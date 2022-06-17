package com.fallframework.platform.starter.wxwork.constant;

/**
 * @author zhuangpf
 */
public class WxworkStarterConstant {

	/**
	 * 缓存key：accesstoken
	 */
	public static final String CACHE_KEY_ACCESS_TOKEN = "wxwork:accesstoken";

	// ----------------------------------------- token ----------------------------------------- //
	/**
	 * 获取token
	 */
	public static final String URL_GETTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";

	// ----------------------------------------- 通讯录管理 ----------------------------------------- //
	/**
	 * 创建成员 POST
	 */
	public static final String URL_USER_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";

	/**
	 * 读取成员 GET
	 */
	public static final String URL_USER_GET = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

	/**
	 * 更新成员 POST
	 */
	public static final String URL_USER_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";

	/**
	 * 删除成员 GET
	 */
	public static final String URL_USER_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";

	/**
	 * 批量删除成员 POST
	 */
	public static final String URL_USER_BATCHDELETE = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";

	/**
	 * 获取部门成员 GET
	 */
	public static final String URL_USER_SIMPLELIST = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

	/**
	 * 获取部门成员详情 GET
	 */
	public static final String URL_USER_LIST = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

	/**
	 * userid与openid互换 POST
	 */
	public static final String URL_USER_CONVERT_TO_OPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=ACCESS_TOKEN";

	/**
	 * 二次验证 GET
	 */
	public static final String URL_USER_AUTHSUCC = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=ACCESS_TOKEN&userid=USERID";

	/**
	 * 邀请成员 POST
	 */
	public static final String URL_BATCH_INVITE = "https://qyapi.weixin.qq.com/cgi-bin/batch/invite?access_token=ACCESS_TOKEN";

	/**
	 * 获取加入企业二维码 GET
	 */
	public static final String URL_CORP_GET_JOIN_QRCODE = "https://qyapi.weixin.qq.com/cgi-bin/corp/get_join_qrcode?access_token=ACCESS_TOKEN&size_type=SIZE_TYPE";

	/**
	 * 手机号获取userid POST
	 */
	public static final String URL_USER_GETUSERID = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=ACCESS_TOKEN";


	/**
	 * 创建部门 POST
	 */
	public static final String URL_DEPARTMENT_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";

	/**
	 * 更新部门 POST
	 */
	public static final String URL_DEPARTMENT_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";

	/**
	 * 删除部门 GET
	 */
	public static final String URL_DEPARTMENT_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";

	/**
	 * 获取部门列表 GET
	 */
	public static final String URL_DEPARTMENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";

	/**
	 * 获取子部门ID列表 GET
	 */
	public static final String URL_DEPARTMENT_SIMPLELIST = "https://qyapi.weixin.qq.com/cgi-bin/department/simplelist?access_token=ACCESS_TOKEN&id=ID";

	/**
	 * 获取单个部门详情 GET
	 */
	public static final String URL_DEPARTMENT_GET = "https://qyapi.weixin.qq.com/cgi-bin/department/get?access_token=ACCESS_TOKEN&id=ID";

	/**
	 * 创建标签 POST
	 */
	public static final String URL_TAG_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN";

	/**
	 * 更新标签名字 POST
	 */
	public static final String URL_TAG_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=ACCESS_TOKEN";

	/**
	 * 删除标签 GET
	 */
	public static final String URL_TAG_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=ACCESS_TOKEN&tagid=TAGID";

	/**
	 * 获取标签成员 GET
	 */
	public static final String URL_TAG_GET = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=TAGID";

	/**
	 * 增加标签成员 POST
	 */
	public static final String URL_TAG_ADDTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=ACCESS_TOKEN";

	/**
	 * 删除标签成员 POST
	 */
	public static final String URL_TAG_DELTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=ACCESS_TOKEN";

	/**
	 * 获取标签列表 GET
	 */
	public static final String URL_TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=ACCESS_TOKEN";


}
