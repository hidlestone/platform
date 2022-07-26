package com.fallframework.platform.starter.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fallframework.platform.starter.data.mp.model.Leaf;
import com.fallframework.platform.starter.sms.entity.SmsHistory;

public interface SmsHistoryService extends IService<SmsHistory> {

	Leaf<SmsHistory> list(SmsHistory smsHistory);

}
