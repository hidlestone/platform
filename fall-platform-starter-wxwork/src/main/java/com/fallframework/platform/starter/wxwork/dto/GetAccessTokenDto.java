package com.fallframework.platform.starter.wxwork.dto;

import com.fallframework.platform.starter.wxwork.model.AccessTokenTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class GetAccessTokenDto {

	/**
	 * 公司ID
	 */
	private String corpId;

	/**
	 * 应用类型
	 */
	private AccessTokenTypeEnum accessTokenType;

	/**
	 * 应用的secret
	 */
	private String secret;

}
