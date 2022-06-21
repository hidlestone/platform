package com.fallframework.platform.starter.wechatwork.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class TokenResponse extends WXStatusResponse {

	private String corpId;

	private Integer expires_in;

	private String access_token;

	private Long loseTime;

	private AccessTokenTypeEnum accessTokenType;

}
