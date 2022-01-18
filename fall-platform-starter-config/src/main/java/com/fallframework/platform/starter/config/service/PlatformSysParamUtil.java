package com.fallframework.platform.starter.config.service;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.cache.redis.util.RedisUtil;
import com.fallframework.platform.starter.config.constant.ConfigStarterConstant;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.mapper.SysParamGroupMapper;
import com.fallframework.platform.starter.config.mapper.SysParamItemMapper;
import com.fallframework.platform.starter.config.model.SysParamGroupResponse;
import com.fallframework.platform.starter.config.model.SysParamItemResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统参数服务
 *
 * @author zhuangpf
 */
@Service
public class PlatformSysParamUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlatformSysParamUtil.class);

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysParamGroupService sysParamGroupService;
	@Autowired
	private SysParamItemMapper sysParamItemMapper;
	@Autowired
	private SysParamGroupMapper sysParamGroupMapper;

	/**
	 * 根据系统参数组，查询组对应的明细项
	 */
	public ResponseResult<List<SysParamItemResponse>> getSysParamGroupItems(String groupCode) {
		List<SysParamItemResponse> sysParamItemList = (List<SysParamItemResponse>) redisUtil.hget(ConfigStarterConstant.SYS_PARAM_CACHE_KEY, groupCode);
		if (CollectionUtil.isEmpty(sysParamItemList)) {
			SysParamGroupResponse sysParamGroupResponse = sysParamGroupService.select(groupCode).getData();
			sysParamItemList = sysParamGroupResponse.getSysParamItemList();
		}
		return ResponseResult.success(sysParamItemList);
	}

	public ResponseResult<Map<String, String>> getSysParamGroupItemMap(String groupCode) {
		List<SysParamItemResponse> sysParamItemList = (List<SysParamItemResponse>) redisUtil.hget(ConfigStarterConstant.SYS_PARAM_CACHE_KEY, groupCode);
		if (CollectionUtil.isEmpty(sysParamItemList)) {
			SysParamGroupResponse sysParamGroupResponse = sysParamGroupService.select(groupCode).getData();
			sysParamItemList = sysParamGroupResponse.getSysParamItemList();
		}
		if (CollectionUtil.isEmpty(sysParamItemList)) {
			throw new RuntimeException("system param " + groupCode + " has not inited.");
		}
		Map<String, String> sysItemMap = sysParamItemList.stream().collect(Collectors.toMap(it -> it.getCode(), it -> it.getValue()));
		return ResponseResult.success(sysItemMap);
	}

	/**
	 * 系统参数明细项
	 */
	public ResponseResult<SysParamItemResponse> getSysParamItem(String groupCode, String itemCode) {
		List<SysParamItemResponse> sysParamItemList = (List<SysParamItemResponse>) redisUtil.hget(ConfigStarterConstant.SYS_PARAM_CACHE_KEY, groupCode);
		SysParamItemResponse sysParamItemResponse = sysParamItemList.stream().filter(it -> itemCode.equals(it.getCode())).findFirst().get();
		if (null == sysParamItemResponse) {
			SysParamItem sysParamItem = sysParamItemMapper.selectById(itemCode);
			BeanUtils.copyProperties(sysParamItem, sysParamItemResponse);
		}
		return ResponseResult.success(sysParamItemResponse);
	}


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
