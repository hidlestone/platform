package com.fallframework.platform.starter.wxwork.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class WXWorkBaseEntity implements Serializable {

	private static final long serialVersionUID = -6247812470913165481L;

	private String errmsg;

	private Integer errcode;

}
