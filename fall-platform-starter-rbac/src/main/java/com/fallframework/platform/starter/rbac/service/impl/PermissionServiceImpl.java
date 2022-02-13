package com.fallframework.platform.starter.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.rbac.constant.RbacStarterConstant;
import com.fallframework.platform.starter.rbac.entity.Permission;
import com.fallframework.platform.starter.rbac.mapper.PermissionMapper;
import com.fallframework.platform.starter.rbac.model.RolePermissionResponse;
import com.fallframework.platform.starter.rbac.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
	private PermissionMapper permissionMapper;
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
		Map<Long, List<RolePermissionResponse>> rolePermMap = new HashMap<>();
		rolePermMap = rolePermissionResponseList.stream().collect(Collectors.groupingBy(RolePermissionResponse::getId));
		for (Map.Entry<Long, List<RolePermissionResponse>> entry : rolePermMap.entrySet()) {
			redisUtil.hset(RbacStarterConstant.CACHE_KEY_ROLE_PERMISSION, String.valueOf(entry.getKey()), entry.getValue());
		}
		LOGGER.info("role permission cache has refreshed.");
		return ResponseResult.success();
	}
}
