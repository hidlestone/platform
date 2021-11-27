package com.fallframework.platform.starter.config.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.config.constant.ConfigStarterConstant;
import com.fallframework.platform.starter.config.entity.SysParamGroup;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.mapper.SysParamGroupMapper;
import com.fallframework.platform.starter.config.mapper.SysParamItemMapper;
import com.fallframework.platform.starter.config.model.SysParamGroupRequest;
import com.fallframework.platform.starter.config.model.SysParamGroupResponse;
import com.fallframework.platform.starter.config.model.SysParamItemRequest;
import com.fallframework.platform.starter.config.model.SysParamItemResponse;
import com.fallframework.platform.starter.config.service.SysParamGroupService;
import com.fallframework.platform.starter.config.service.SysParamItemService;
import com.fallframework.platform.starter.core.entity.request.BasePageRequest;
import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhuangpf
 */
@Service
public class SysParamGroupServiceImpl extends ServiceImpl<SysParamGroupMapper, SysParamGroup> implements SysParamGroupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysParamGroupServiceImpl.class);

	@Autowired
	private SysParamGroupMapper sysParamGroupMapper;
	@Autowired
	private SysParamItemMapper sysParamItemMapper;
	@Autowired
	private SysParamItemService sysParamItemService;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public ResponseResult insert(SysParamGroupRequest request) {
		SysParamGroup sysParamGroup = new SysParamGroup();
		BeanUtils.copyProperties(request, sysParamGroup);
		List<SysParamItemRequest> sysParamItemRequestList = request.getSysParamItemList();
		List<SysParamItem> sysParamItemList = JSON.parseArray(JSON.toJSONString(sysParamItemRequestList), SysParamItem.class);
		sysParamGroupMapper.insert(sysParamGroup);
		sysParamItemService.saveBatch(sysParamItemList);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult delete(String code) {
		sysParamGroupMapper.deleteById(code);
		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("group_code", code);
		sysParamItemMapper.delete(wrapper);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult update(SysParamGroupRequest request) {
		SysParamGroup sysParamGroup = new SysParamGroup();
		BeanUtils.copyProperties(request, sysParamGroup);
		List<SysParamItemRequest> sysParamItemRequestList = request.getSysParamItemList();
		List<SysParamItem> sysParamItemList = JSON.parseArray(JSON.toJSONString(sysParamItemRequestList), SysParamItem.class);
		sysParamGroupMapper.updateById(sysParamGroup);
		sysParamItemService.updateBatchById(sysParamItemList);
		return ResponseResult.success();
	}

	@Override
	public SysParamGroupResponse get(String code) {
		// 系统参数组
		SysParamGroupResponse sysParamGroupResponse = sysParamGroupMapper.findByCode(code);
		// 系统参数组对应的明细项
		List<SysParamItemResponse> sysParamItemList = sysParamItemMapper.findItemsByGroupCode(code);
		sysParamGroupResponse.setSysParamItemList(sysParamItemList);
		return sysParamGroupResponse;
	}

	@Override
	public List<SysParamGroupResponse> groupList(BasePageRequest pageRequest) {
		Page<SysParamGroupResponse> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
		return sysParamGroupMapper.groupList(page, pageRequest);
	}

	@Override
	public List<SysParamGroupResponse> groupItemsList(BasePageRequest pageRequest) {
		Page<SysParamGroupResponse> page = new Page<>(pageRequest.getPageNum(), pageRequest.getPageSize());
		List<SysParamGroupResponse> sysParamGroupList = sysParamGroupMapper.groupList(page, pageRequest);
		if (CollectionUtil.isNotEmpty(sysParamGroupList)) {
			List<String> groupCodeList = sysParamGroupList.stream().map(it -> it.getCode()).collect(Collectors.toList());
			List<SysParamItemResponse> sysParamItemList = sysParamItemMapper.findItemsByGroupCodeList(groupCodeList);
			Map<String, List<SysParamItemResponse>> sysParamGroupMap = sysParamItemList.stream().collect(Collectors.groupingBy(e -> e.getGroupCode()));
			for (SysParamGroupResponse sysParamGroup : sysParamGroupList) {
				List<SysParamItemResponse> sysParamItems = sysParamGroupMap.get(sysParamGroup.getCode());
				sysParamGroup.setSysParamItemList(sysParamItems);
			}
		}
		return sysParamGroupList;
	}

	@Override
	public ResponseResult refreshSysParamCache() {
		List<SysParamGroupResponse> sysParamGroupResponseList = sysParamGroupMapper.findAllSysParamGroup();
		redisUtil.del(ConfigStarterConstant.SYS_PARAM_CACHE_KEY);
		for (SysParamGroupResponse sysParamGroup : sysParamGroupResponseList) {
			redisUtil.hset(ConfigStarterConstant.SYS_PARAM_CACHE_KEY, sysParamGroup.getCode(), sysParamGroup.getSysParamItemList());
		}
		LOGGER.info("system global parameters cache has refreshed.");
		return ResponseResult.success();
	}

}
