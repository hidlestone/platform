package com.fallframework.platform.starter.wechatwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 企微通讯录-部门-成员<br/>
 * 详见：https://developer.work.weixin.qq.com/document/path/90196
 */
@Getter
@Setter
@TableName("wxwork_user")
public class User extends WXWorkBaseEntity {

	private static final long serialVersionUID = -8397056354063579252L;

	/**
	 * 成员userid
	 */
	@TableId(value = "userid", type = IdType.INPUT)
	private String userid;

	/**
	 * 成员名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 职务信息
	 */
	@TableField("position")
	private String position;

	/**
	 * 手机号码
	 */
	@TableField("mobile")
	private String mobile;

	/**
	 * 性别
	 */
	@TableField("gender")
	private String gender;

	/**
	 * 邮箱
	 */
	@TableField("email")
	private String email;

	/**
	 * 企业邮箱
	 */
	@TableField("biz_mail")
	private String bizMail;

	/**
	 * 直属上级UserID
	 */
	@TableField("direct_leader")
	private String directLeader;

	/**
	 * 头像url
	 */
	@TableField("avatar")
	private String avatar;

	/**
	 * 头像缩略图url
	 */
	@TableField("thumb_avatar")
	private String thumbAvatar;

	/**
	 * 座机
	 */
	@TableField("telephone")
	private String telephone;

	/**
	 * 别名
	 */
	@TableField("alias")
	private String alias;

	/**
	 * 地址
	 */
	@TableField("address")
	private String address;

	/**
	 * 全局唯一
	 */
	@TableField("open_userid")
	private String openUserid;

	/**
	 * 主部门
	 */
	@TableField("main_department")
	private Long mainDepartment;

	/**
	 * 扩展属性
	 */
	@TableField("extattr")
	private String extattr;

	/**
	 * 激活状态
	 */
	@TableField("status")
	private Byte status;

	/**
	 * 员工个人二维码
	 */
	@TableField("qr_code")
	private String qrCode;

	/**
	 * 对外职务
	 */
	@TableField("external_position")
	private String externalPosition;

	/**
	 * 成员对外属性
	 */
	@TableField("external_profile")
	private String externalProfile;

}