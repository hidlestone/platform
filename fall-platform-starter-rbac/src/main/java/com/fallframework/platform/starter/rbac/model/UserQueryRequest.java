package com.fallframework.platform.starter.rbac.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.request.BasePageRequest;

import java.util.Date;

/**
 * @author zhuangpf
 */
public class UserQueryRequest extends BasePageRequest {

	private static final long serialVersionUID = 7090893306708519198L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 用户昵称
	 */
	private String username;

	/**
	 * 头像URL
	 */
	private String avatar;

	/**
	 * 电话号码
	 */
	private String tel;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 身份证号
	 */
	private String idCard;

	/**
	 * 真实姓名
	 */
	private String name;

	/**
	 * 生日开始日期
	 */
	private Date birthdayStart;

	/**
	 * 生日结束日期
	 */
	private Date birthdayEnd;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 性别
	 */
	private SexEnum sex;

	/**
	 * 状态
	 */
	private StatusEnum status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 用户来源
	 */
	private Byte sourceType;

	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;

}
