package com.fallframework.platform.starter.wxwork.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 详见：https://developer.work.weixin.qq.com/document/path/90196
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class User extends WXWorkBaseEntity {

	private static final long serialVersionUID = -8397056354063579252L;

	// 成员UserID。对应管理端的帐号
	private String userid;
	// 成员名称；第三方不可获取，调用时返回userid以代替name；代开发自建应用需要管理员授权才返回；对于非第三方创建的成员，第三方通讯录应用也不可获取；未返回name的情况需要通过通讯录展示组件来展示名字
	private String name;
	// 手机号码，代开发自建应用需要管理员授权才返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String mobile;        // 手机号码，第三方仅通讯录应用可获取
	// 成员所属部门id列表，仅返回该应用有查看权限的部门id；成员授权模式下，固定返回根部门id，即固定为1。对授权了“组织架构信息”权限的第三方应用，返回成员所属的全部部门id
	private Integer[] department;
	// 部门内的排序值，默认为0。数量必须和department一致，数值越大排序越前面。值范围是0, 2^32)。[成员授权模式下不返回该字段
	private Integer[] order;
	// 职务信息；代开发自建应用需要管理员授权才返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String position;
	// 性别。0表示未定义，1表示男性，2表示女性。第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段。注：不可获取指返回值0
	private String gender;
	// 邮箱，代开发自建应用需要管理员授权才返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String email;
	// 企业邮箱，代开发自建应用不返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String biz_mail;
	// 表示在所在的部门内是否为部门负责人，数量与department一致；第三方通讯录应用或者授权了“组织架构信息-应用可获取企业的部门组织架构信息-部门负责人”权限的第三方应用可获取；对于非第三方创建的成员，第三方通讯录应用不可获取；上游企业不可获取下游企业成员该字段
	private Integer[] is_leader_in_dept;    //	表示在所在的部门内是否为上级；第三方仅通讯录应用可获取
	// 头像url。 第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取
	private String avatar;
	// 头像缩略图url。第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取
	private String thumb_avatar;
	// 座机。代开发自建应用需要管理员授权才返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String telephone;
	// 别名；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String alias;
	//	扩展属性，第三方仅通讯录应用可获取
	private Object extattr;
	// 激活状态: 1=已激活，2=已禁用，4=未激活，5=退出企业。已激活代表已激活企业微信或已关注微信插件（原企业号）。未激活代表既未激活企业微信又未关注微信插件（原企业号）。
	private Integer status;
	// 	员工个人二维码，扫描可添加为外部联系人(注意返回的是一个url，可在浏览器上打开该url以展示二维码)；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String qr_code;
	// 成员对外属性，字段详情见对外属性；代开发自建应用需要管理员授权才返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private Object external_profile;
	// 对外职务，如果设置了该值，则以此作为对外展示的职务，否则以position来展示。代开发自建应用需要管理员授权才返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String external_position;
	// 地址。代开发自建应用需要管理员授权才返回；第三方仅通讯录应用可获取；对于非第三方创建的成员，第三方通讯录应用也不可获取；上游企业不可获取下游企业成员该字段
	private String address;
	// 全局唯一。对于同一个服务商，不同应用获取到企业内同一个成员的open_userid是相同的，最多64个字节。仅第三方应用可获取
	private String open_userid;
	// 主部门，仅当应用对主部门有查看权限时返回。
	private String main_department;

}
