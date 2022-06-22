package com.fallframework.platform.starter.wechatwork.constant;

/**
 * @author zhuangpf
 */
public class WechatWorkStarterConstant {

	/**
	 * 缓存key：accesstoken
	 */
	public static final String CACHE_KEY_ACCESS_TOKEN = "wechatwork:accesstoken";

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

	/**
	 * 增量更新成员 POST
	 */
	public static final String URL_BATCH_SYNCUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/syncuser?access_token=ACCESS_TOKEN";

	/**
	 * 全量覆盖成员 POST
	 */
	public static final String URL_BATCH_REPLACEUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceuser?access_token=ACCESS_TOKEN";

	/**
	 * 全量覆盖部门 POST
	 */
	public static final String URL_BATCH_REPLACEPARTY = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceparty?access_token=ACCESS_TOKEN";

	/**
	 * 获取异步任务结果 GET
	 */
	public static final String URL_BATCH_GETRESULT = "https://qyapi.weixin.qq.com/cgi-bin/batch/getresult?access_token=ACCESS_TOKEN&jobid=JOBID";

	/**
	 * 获取应用的可见范围 POST
	 */
	public static final String URL_LINKEDCORP_AGENT_GET_PERM_LIST = "https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/agent/get_perm_list?access_token=ACCESS_TOKEN";

	/**
	 * 获取互联企业成员详细信息 POST
	 */
	public static final String URL_LINKEDCORP_USER_GET = "https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/user/get?access_token=ACCESS_TOKEN";

	/**
	 * 获取互联企业部门成员 POST
	 */
	public static final String URL_LINKEDCORP_USER_SIMPLELIST = "https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/user/simplelist?access_token=ACCESS_TOKEN";

	/**
	 * 获取互联企业部门成员详情 POST
	 */
	public static final String URL_LINKEDCORP_USER_LIST = "https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/user/list?access_token=ACCESS_TOKEN";

	/**
	 * 获取互联企业部门列表 POST
	 */
	public static final String URL_LINKEDCORP_DEPARTMENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/linkedcorp/department/list?access_token=ACCESS_TOKEN";

	/**
	 * 导出成员 POST
	 */
	public static final String URL_EXPORT_SIMPLE_USER = "https://qyapi.weixin.qq.com/cgi-bin/export/simple_user?access_token=ACCESS_TOKEN";

	/**
	 * 导出成员详情 POST
	 */
	public static final String URL_EXPORT_USER = "https://qyapi.weixin.qq.com/cgi-bin/export/user?access_token=ACCESS_TOKEN";

	/**
	 * 导出部门 POST
	 */
	public static final String URL_EXPORT_DEPARTMENT = "https://qyapi.weixin.qq.com/cgi-bin/export/department?access_token=ACCESS_TOKEN";

	/**
	 * 导出标签成员 POST
	 */
	public static final String URL_EXPORT_TAGUSER = "https://qyapi.weixin.qq.com/cgi-bin/export/taguser?access_token=ACCESS_TOKEN";

	/**
	 * 获取导出结果 GET
	 */
	public static final String URL_EXPORT_GET_RESULT = "https://qyapi.weixin.qq.com/cgi-bin/export/get_result?access_token=ACCESS_TOKEN&jobid=JOBID";

	// ----------------------------------------- 客户联系 ----------------------------------------- //
	/**
	 * 获取配置了客户联系功能的成员列表 GET
	 */
	public static final String URL_EXTERNALCONTACT_GET_FOLLOW_USER_LIST = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_follow_user_list?access_token=ACCESS_TOKEN";

	/**
	 * 配置客户联系「联系我」方式 POST
	 */
	public static final String URL_EXTERNALCONTACT_ADD_CONTACT_WAY = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_contact_way?access_token=ACCESS_TOKEN";

	/**
	 * 获取企业已配置的「联系我」方式 POST
	 */
	public static final String URL_EXTERNALCONTACT_GET_CONTACT_WAY = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_contact_way?access_token=ACCESS_TOKEN";

	/**
	 * 获取企业已配置的「联系我」列表 POST
	 */
	public static final String URL_EXTERNALCONTACT_LIST_CONTACT_WAY = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list_contact_way?access_token=ACCESS_TOKEN";

	/**
	 * 更新企业已配置的「联系我」方式 POST
	 */
	public static final String URL_EXTERNALCONTACT_UPDATE_CONTACT_WAY = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/update_contact_way?access_token=ACCESS_TOKEN";

	/**
	 * 删除企业已配置的「联系我」方式
	 */
	public static final String URL_EXTERNALCONTACT_DEL_CONTACT_WAY = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/del_contact_way?access_token=ACCESS_TOKEN";

	/**
	 * 结束临时会话
	 */
	public static final String URL_EXTERNALCONTACT_CLOSE_TEMP_CHAT = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/close_temp_chat?access_token=ACCESS_TOKEN";

	/**
	 * 获取客户列表 GET
	 */
	public static final String URL_EXTERNALCONTACT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list?access_token=ACCESS_TOKEN&userid=USERID";

	/**
	 * 获取客户详情 GET
	 */
	public static final String URL_EXTERNALCONTACT_GET = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get?access_token=ACCESS_TOKEN&external_userid=EXTERNAL_USERID&cursor=CURSOR";

	/**
	 * 批量获取客户详情 POST
	 */
	public static final String URL_EXTERNALCONTACT_GET_BY_USER = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/batch/get_by_user?access_token=ACCESS_TOKEN";

	/**
	 * 修改客户备注信息 POST
	 */
	public static final String URL_EXTERNALCONTACT_REMARK = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/remark?access_token=ACCESS_TOKEN";

	/**
	 * 获取规则组列表 POST
	 */
	public static final String URL_EXTERNALCONTACT_CUSTOMER_STRATEGY = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/customer_strategy/list?access_token=ACCESS_TOKEN";

	/**
	 * 获取企业标签库 POST
	 */
	public static final String URL_EXTERNALCONTACT_GET_CORP_TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_corp_tag_list?access_token=ACCESS_TOKEN";

	/**
	 * 获取指定规则组下的企业客户标签 POST
	 */
	public static final String URL_EXTERNALCONTACT_GET_STRATEGY_TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_strategy_tag_list?access_token=ACCESS_TOKEN";

	/**
	 * 编辑客户企业标签 POST
	 */
	public static final String URL_EXTERNALCONTACT_MARK_TAG = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/mark_tag?access_token=ACCESS_TOKEN";

	/**
	 * 分配在职成员的客户 POST
	 */
	public static final String URL_EXTERNALCONTACT_TRANSFER_CUSTOMER = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/transfer_customer?access_token=ACCESS_TOKEN";

	/**
	 * 查询客户接替状态 POST
	 */
	public static final String URL_EXTERNALCONTACT_TRANSFER_RESULT = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/transfer_result?access_token=ACCESS_TOKEN";

	/**
	 * 分配在职成员的客户群 POST
	 */
	public static final String URL_EXTERNALCONTACT_ONJOB_TRANSFER = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/onjob_transfer?access_token=ACCESS_TOKEN";

	/**
	 * 获取待分配的离职成员列表 POST
	 */
	public static final String URL_EXTERNALCONTACT_GET_UNASSIGNED_LIST = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_unassigned_list?access_token=ACCESS_TOKEN";

	/**
	 * 分配离职成员的客户 POST
	 */
	public static final String URL_EXTERNALCONTACT_RESIGNED_TRANSFER_CUSTOMER = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/resigned/transfer_customer?access_token=ACCESS_TOKEN";

	/**
	 * 查询客户接替状态 POST
	 */
	public static final String URL_EXTERNALCONTACT_RESIGNED_TRANSFER_RESULT = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/resigned/transfer_result?access_token=ACCESS_TOKEN";

	/**
	 * 分配离职成员的客户群 POST
	 */
	public static final String URL_EXTERNALCONTACT_GROUPCHAT_TRANSFER = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/transfer?access_token=ACCESS_TOKEN";

	
	
	

}
