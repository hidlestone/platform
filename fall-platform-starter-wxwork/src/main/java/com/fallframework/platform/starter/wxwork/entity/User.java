package com.fallframework.platform.starter.wxwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 详见：https://developer.work.weixin.qq.com/document/path/90196
 */
@Getter
@Setter
@TableName("wxwork_user")
public class User extends BaseEntity {
	private static final long serialVersionUID = -8397056354063579252L;
	/**
	 * 成员UserID
	 */
	@TableId(value = "userid", type = IdType.ASSIGN_ID)
	private String userid;

	/**
	 * 成员名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 成员所属部门id列表
	 */
	@TableField("mobile")
	private String mobile;

	/**
	 * 成员UserID
	 */
	@TableField("department")
	private String department;

	/**
	 * 部门内的排序值，默认为0
	 */
	@TableField("order")
	private String order;

	/**
	 * 职务信息
	 */
	@TableField("position")
	private String position;

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
	 * 表示在所在的部门内是否为部门负责人
	 */
	@TableField("is_leader_in_dept")
	private String isLeaderInDept;

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
	 * 扩展属性
	 */
	@TableField("extattr")
	private String extattr;

	/**
	 * 激活状态
	 */
	@TableField("status")
	private Boolean status;

	/**
	 * 员工个人二维码
	 */
	@TableField("qr_code")
	private String qrCode;

	/**
	 * 成员对外属性
	 */
	@TableField("external_profile")
	private String externalProfile;

	/**
	 * 对外职务
	 */
	@TableField("external_position")
	private String externalPosition;

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
	private String mainDepartment;

}