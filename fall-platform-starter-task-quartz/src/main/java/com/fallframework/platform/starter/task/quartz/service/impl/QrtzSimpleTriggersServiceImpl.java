package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzSimpleTriggersMapper;
import com.fallframework.platform.starter.task.quartz.entity.QrtzSimpleTriggers;
import com.fallframework.platform.starter.task.quartz.service.QrtzSimpleTriggersService;

@Service
public class QrtzSimpleTriggersServiceImpl extends ServiceImpl<QrtzSimpleTriggersMapper, QrtzSimpleTriggers> implements QrtzSimpleTriggersService {

}

