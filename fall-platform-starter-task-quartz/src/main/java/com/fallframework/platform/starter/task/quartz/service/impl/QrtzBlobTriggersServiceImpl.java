package com.fallframework.platform.starter.task.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.entity.QrtzBlobTriggers;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzBlobTriggersMapper;
import com.fallframework.platform.starter.task.quartz.service.QrtzBlobTriggersService;
import org.springframework.stereotype.Service;

@Service
public class QrtzBlobTriggersServiceImpl extends ServiceImpl<QrtzBlobTriggersMapper, QrtzBlobTriggers> implements QrtzBlobTriggersService {

}

