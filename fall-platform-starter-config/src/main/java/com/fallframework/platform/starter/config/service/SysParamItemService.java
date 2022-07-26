package com.fallframework.platform.starter.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.config.entity.SysParamItem;

public interface SysParamItemService extends IService<SysParamItem> {

	SysParamItem get(String code);

	Leaf<SysParamItem> list(SysParamItem sysParamItem);

}
