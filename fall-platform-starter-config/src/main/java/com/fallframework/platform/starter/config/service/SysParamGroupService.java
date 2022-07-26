package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.config.entity.SysParamGroup;

/**
 * @author zhuangpf
 */
public interface SysParamGroupService extends IService<SysParamGroup> {

	SysParamGroup get(String code);

	Leaf<SysParamGroup> list(SysParamGroup sysParamGroup);

}
