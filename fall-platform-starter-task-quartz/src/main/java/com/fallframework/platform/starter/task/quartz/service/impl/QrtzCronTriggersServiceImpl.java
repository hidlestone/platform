package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzCronTriggersMapper;
import com.fallframework.platform.starter.task.quartz.entity.QrtzCronTriggers;
import com.fallframework.platform.starter.task.quartz.service.QrtzCronTriggersService;

@Service
public class QrtzCronTriggersServiceImpl extends ServiceImpl<QrtzCronTriggersMapper, QrtzCronTriggers> implements QrtzCronTriggersService {

}

