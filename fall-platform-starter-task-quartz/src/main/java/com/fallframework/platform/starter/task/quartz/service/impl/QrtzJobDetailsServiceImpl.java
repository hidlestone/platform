package com.fallframework.platform.starter.task.quartz.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fallframework.platform.starter.task.quartz.entity.QrtzJobDetails;
import com.fallframework.platform.starter.task.quartz.mapper.QrtzJobDetailsMapper;
import com.fallframework.platform.starter.task.quartz.service.QrtzJobDetailsService;

@Service
public class QrtzJobDetailsServiceImpl extends ServiceImpl<QrtzJobDetailsMapper, QrtzJobDetails> implements QrtzJobDetailsService {

}

