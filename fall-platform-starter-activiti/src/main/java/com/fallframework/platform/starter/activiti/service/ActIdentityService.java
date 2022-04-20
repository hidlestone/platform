package com.fallframework.platform.starter.activiti.service;

import com.fallframework.platform.starter.activiti.model.GroupQueryRequest;
import com.fallframework.platform.starter.activiti.model.SaveUserAndGroupRequest;
import com.fallframework.platform.starter.activiti.model.UserQueryRequest;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

/**
 * 用户&用户组管理
 *
 * @author zhuangpf
 */
public interface ActIdentityService {

	/**
	 * 保存用户
	 *
	 * @param user 用户
	 * @return 是否保存成功
	 */
	ResponseResult saveUser(User user);

	/**
	 * 删除用户
	 *
	 * @param user 用户信息
	 * @return 是否删除成功
	 */
	ResponseResult deleteUser(User user);

	/**
	 * 根据用户ID查询用户
	 *
	 * @param userId 用户ID
	 * @return 用户信息
	 */
	ResponseResult<User> getUser(String userId);

	/**
	 * 分页查询用户信息
	 *
	 * @param request 请求参数
	 * @return 用户分页
	 */
	ResponseResult<Leaf<User>> getUserList(UserQueryRequest request);

	/**
	 * 添加组（角色）
	 *
	 * @param group 用户组
	 * @return 是否添加成功红
	 */
	ResponseResult saveGroup(Group group);

	/**
	 * 删除用户组
	 *
	 * @param group 用户组
	 * @return 是否删除成功
	 */
	ResponseResult deleteGroup(Group group);

	/**
	 * 根据用户组ID查询用户组
	 *
	 * @param groupId 用户组ID
	 * @return 用户信息
	 */
	ResponseResult<Group> getGroup(String groupId);

	/**
	 * 分页查询用户组
	 *
	 * @param request 请求参数
	 * @return 用户组分页
	 */
	ResponseResult<Leaf<Group>> getGroupList(GroupQueryRequest request);

	/**
	 * 保存用户和用户组之间的关系
	 *
	 * @param userId  用户ID
	 * @param groupId 用户组ID
	 * @return 是否保存成功
	 */
	ResponseResult saveUserGroupMemership(String userId, String groupId);

	/**
	 * 保存用户&用户组&关系
	 *
	 * @param request 请求参数
	 * @return 是否保存成功
	 */
	ResponseResult saveUserAndGroup(SaveUserAndGroupRequest request);

}
