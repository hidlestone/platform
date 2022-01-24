package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzLocksMapper;
import com.fallframework.platform.starter.task.quartz.entity.QrtzLocks;
import com.fallframework.platform.starter.task.quartz.service.QrtzLocksService;

@Service
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksMapper, QrtzLocks> implements QrtzLocksService {

}

