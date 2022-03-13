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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统参数服务工具类
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
	 * 初始化该实例的时候就去更新系统参数缓存<br/>
	 * 以便其他配置类在后续步骤可以获取到相关参数。
	 */
	public PlatformSysParamUtil(RedisUtil redisUtil, SysParamGroupService sysParamGroupService, SysParamItemMapper sysParamItemMapper, SysParamGroupMapper sysParamGroupMapper) {
		this.redisUtil = redisUtil;
		this.sysParamGroupService = sysParamGroupService;
		this.sysParamItemMapper = sysParamItemMapper;
		this.sysParamGroupMapper = sysParamGroupMapper;
		// 刷新系统参数缓存
		this.refreshSysParamCache();
	}

	/**
	 * @param groupCode 参数组编码
	 * @return 系统参数明细的map形式
	 */
	public ResponseResult<Map<String, String>> getSysParamGroupItemMap(String groupCode) {
		List<SysParamItemResponse> sysParamItemList = (List<SysParamItemResponse>) redisUtil.hget(ConfigStarterConstant.CACHE_KEY_SYS_PARAM, groupCode);
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
	 *
	 * @param groupCode 参数组编码
	 * @param itemCode  明细项编码
	 * @return 系统参数明细项信息
	 */
	public ResponseResult<SysParamItemResponse> getSysParamItem(String groupCode, String itemCode) {
		List<SysParamItemResponse> sysParamItemList = (List<SysParamItemResponse>) redisUtil.hget(ConfigStarterConstant.CACHE_KEY_SYS_PARAM, groupCode);
		SysParamItemResponse sysParamItemResponse = sysParamItemList.stream().filter(it -> itemCode.equals(it.getCode())).findFirst().get();
		if (null == sysParamItemResponse) {
			SysParamItem sysParamItem = sysParamItemMapper.selectById(itemCode);
			BeanUtils.copyProperties(sysParamItem, sysParamItemResponse);
		}
		return ResponseResult.success(sysParamItemResponse);
	}

	/**
	 * 刷新系统参数缓存
	 *
	 * @return 是否更新系统参数缓存成功
	 */
	public ResponseResult refreshSysParamCache() {
		List<SysParamGroupResponse> sysParamGroupResponseList = sysParamGroupMapper.findAllSysParamGroup();
		redisUtil.del(ConfigStarterConstant.CACHE_KEY_SYS_PARAM);
		for (SysParamGroupResponse sysParamGroup : sysParamGroupResponseList) {
			redisUtil.hset(ConfigStarterConstant.CACHE_KEY_SYS_PARAM, sysParamGroup.getCode(), sysParamGroup.getSysParamItemList());
		}
		LOGGER.info("system global parameters cache has refreshed.");
		return ResponseResult.success();
	}

	/**
	 * @param sysItemMap   参数组
	 * @param sysParamItem 参数项
	 * @return 参数项中的值
	 */
	public String mapGet(Map<String, String> sysItemMap, String sysParamItem) {
		String value = sysItemMap.get(sysParamItem);
		if (StringUtils.isEmpty(value)) {
			throw new RuntimeException("system param item " + sysParamItem + " is no exist.");
		}
		return value;
	}
}
