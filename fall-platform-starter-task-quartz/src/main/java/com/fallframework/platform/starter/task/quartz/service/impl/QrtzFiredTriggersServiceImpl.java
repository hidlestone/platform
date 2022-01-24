package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.entity.QrtzFiredTriggers;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzFiredTriggersMapper;
import com.fallframework.platform.starter.task.quartz.service.QrtzFiredTriggersService;

@Service
public class QrtzFiredTriggersServiceImpl extends ServiceImpl<QrtzFiredTriggersMapper, QrtzFiredTriggers> implements QrtzFiredTriggersService {

}

