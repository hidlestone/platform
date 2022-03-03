package com.fallframework.platform.starter.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.rbac.constant.RbacStarterConstant;
import com.fallframework.platform.starter.rbac.entity.Permission;
import com.fallframework.platform.starter.rbac.entity.UserRole;
import com.fallframework.platform.starter.rbac.mapper.PermissionMapper;
import com.fallframework.platform.starter.rbac.mapper.UserRoleMapper;
import com.fallframework.platform.starter.rbac.model.RolePermissionResponse;
import com.fallframework.platform.starter.rbac.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public ResponseResult refreshPermissionCache() {
		// 获取所有角色权限
		List<RolePermissionResponse> rolePermissionResponseList = permissionMapper.selectAllRolePermission();
		if (CollectionUtil.isEmpty(rolePermissionResponseList)) {
			return ResponseResult.success();
		}
		// 按照角色分组
		Map<Long, List<RolePermissionResponse>> rolePermMap = 
				rolePermissionResponseList.stream().collect(Collectors.groupingBy(RolePermissionResponse::getId));
		for (Map.Entry<Long, List<RolePermissionResponse>> entry : rolePermMap.entrySet()) {
			redisUtil.hset(RbacStarterConstant.CACHE_KEY_ROLE_PERMISSION, String.valueOf(entry.getKey()), entry.getValue());
		}
		LOGGER.info("role permission cache has refreshed.");
		return ResponseResult.success();
	}

	@Override
	public List<RolePermissionResponse> getPermissionListByUserId(Long id) {
		// 1、从缓存中获取
		// 当前用户拥有的角色
		Wrapper<UserRole> wrapper = new QueryWrapper<>();
		List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
		// 所有的权限
		List<RolePermissionResponse> allRolePermList = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(userRoles)) {
			for (UserRole item : userRoles) {
				allRolePermList.addAll((List<RolePermissionResponse>) redisUtil.hget(RbacStarterConstant.CACHE_KEY_ROLE_PERMISSION, String.valueOf(item.getRoleId())));
			}
		}
		if (CollectionUtil.isNotEmpty(allRolePermList)) {
			// 权限去重
			allRolePermList = allRolePermList.stream().collect(Collectors.collectingAndThen(
					Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RolePermissionResponse::getPermissionCode))), ArrayList::new));
			return allRolePermList;
		}
		// 2、从数据库获取
		allRolePermList = permissionMapper.getPermissionListByUserId(id);
		return allRolePermList;
	}
}