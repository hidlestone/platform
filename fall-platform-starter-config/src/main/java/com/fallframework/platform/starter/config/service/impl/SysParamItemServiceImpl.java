package com.fallframework.platform.starter.config.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.config.entity.SysParamItem;
import com.fallframework.platform.starter.config.mapper.SysParamItemMapper;
import com.fallframework.platform.starter.config.service.SysParamItemService;
import org.springframework.stereotype.Service;

@Service
public class SysParamItemServiceImpl extends ServiceImpl<SysParamItemMapper, SysParamItem> implements SysParamItemService {

}
