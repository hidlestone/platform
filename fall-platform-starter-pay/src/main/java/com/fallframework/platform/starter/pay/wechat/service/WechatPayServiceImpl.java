package com.fallframework.platform.starter.pay.wechat.service;

import com.fallframework.platform.starter.core.entity.response.ResponseResult;
import com.fallframework.platform.starter.pay.api.PayService;
import com.fallframework.platform.starter.pay.model.PayNotifyResponse;
import com.fallframework.platform.starter.pay.util.WebPropertiesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author zhuangpf
 */
public class WechatPayServiceImpl implements PayService {

	private Log LOGGER = LogFactory.getLog(WechatPayServiceImpl.class);

	// 通知url必须为直接可访问的url，不能携带参数
	private String notifyUrl = WebPropertiesUtil.getInstance().getValue("wechat.pay.notify.url");
	
	

	@Override
	public ResponseResult pay() {
		
		// 订单状态
		
		
		return null;
	}

	@Override
	public String getOutTradeNo(String notify) {
		return null;
	}

	@Override
	public PayNotifyResponse parse() {
		return null;
	}

	@Override
	public boolean synchronize() {
		return false;
	}
}
