package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.entity.QrtzSimpropTriggers;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzSimpropTriggersMapper;
import com.fallframework.platform.starter.task.quartz.service.QrtzSimpropTriggersService;

@Service
public class QrtzSimpropTriggersServiceImpl extends ServiceImpl<QrtzSimpropTriggersMapper, QrtzSimpropTriggers> implements QrtzSimpropTriggersService {

}

