package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.entity.QrtzTriggers;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzTriggersMapper;
import com.fallframework.platform.starter.task.quartz.service.QrtzTriggersService;

@Service
public class QrtzTriggersServiceImpl extends ServiceImpl<QrtzTriggersMapper, QrtzTriggers> implements QrtzTriggersService {

}

