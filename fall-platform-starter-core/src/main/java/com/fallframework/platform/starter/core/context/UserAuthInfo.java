package com.fallframework.platform.starter.core.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户认证信息
 *
 * @author zhuangpf
 * @date 2021/5/14 20:35
 */
@Getter
@Setter
public class UserAuthInfo implements Serializable {

	private static final long serialVersionUID = -2496325779293689499L;

	private String token;
	private Long userId;
	private String userCode;
	private String userName;
	private String clientCode;
	private String roleCodes;
	private String roleNames;
	private String roleIds;
	private String mobile;
	private String email;
	private Map<String, List<Map<String, Object>>> fieldPermissions;
	private Integer loginedFlag;
	private String languageCode;
	private String appId;
	private String accessKey;
	private String secret;
	private String version;

}
