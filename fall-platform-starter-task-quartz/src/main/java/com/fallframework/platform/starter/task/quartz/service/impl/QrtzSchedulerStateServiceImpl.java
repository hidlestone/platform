package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzSchedulerStateMapper;
import com.fallframework.platform.starter.task.quartz.entity.QrtzSchedulerState;
import com.fallframework.platform.starter.task.quartz.service.QrtzSchedulerStateService;

@Service
public class QrtzSchedulerStateServiceImpl extends ServiceImpl<QrtzSchedulerStateMapper, QrtzSchedulerState> implements QrtzSchedulerStateService {

}

