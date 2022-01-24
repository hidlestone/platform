package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.entity.QrtzCalendars;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzCalendarsMapper;
import com.fallframework.platform.starter.task.quartz.service.QrtzCalendarsService;

@Service
public class QrtzCalendarsServiceImpl extends ServiceImpl<QrtzCalendarsMapper, QrtzCalendars> implements QrtzCalendarsService {

}

