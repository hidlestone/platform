package com.fallframework.platform.starter.pay.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */

@Getter
@Setter
public class PayTypeInVo extends BasePageRequest {

	private static final long serialVersionUID = 812883076873188377L;

	private Byte status;

}
