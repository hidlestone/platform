package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.GroupQueryRequest;
import com.fallframework.platform.starter.activiti.model.SaveUserAndGroupRequest;
import com.fallframework.platform.starter.activiti.model.UserQueryRequest;
import com.fallframework.platform.starter.activiti.service.ActIdentityService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuangpf
 */
@Service
public class ActIdentityServiceImpl implements ActIdentityService {

	@Autowired
	private IdentityService identityService;

	@Override
	public ResponseResult saveUser(User user) {
		identityService.saveUser(user);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deleteUser(User user) {
		identityService.deleteUser(user.getId());
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<User> getUser(String userId) {
		User user = identityService.createUserQuery().userId(userId).singleResult();
		return ResponseResult.success(user);
	}

	@Override
	public ResponseResult<Leaf<User>> getUserList(UserQueryRequest request) {
		UserQuery userQuery = identityService.createUserQuery();
		if (StringUtils.isNotEmpty(request.getId())) {
			userQuery.userId(request.getId());
		}
		if (StringUtils.isNotEmpty(request.getFirstName())) {
			userQuery.userFirstName(request.getFirstName());
		}
		if (StringUtils.isNotEmpty(request.getFirstNameLike())) {
			userQuery.userFirstNameLike(request.getFirstNameLike());
		}
		if (StringUtils.isNotEmpty(request.getLastName())) {
			userQuery.userLastName(request.getLastName());
		}
		if (StringUtils.isNotEmpty(request.getLastNameLike())) {
			userQuery.userLastNameLike(request.getLastNameLike());
		}
		if (StringUtils.isNotEmpty(request.getFullNameLike())) {
			userQuery.userFullNameLike(request.getFullNameLike());
		}
		if (StringUtils.isNotEmpty(request.getEmail())) {
			userQuery.userEmail(request.getEmail());
		}
		if (StringUtils.isNotEmpty(request.getEmailLike())) {
			userQuery.userEmailLike(request.getEmailLike());
		}
		if (StringUtils.isNotEmpty(request.getGroupId())) {
			userQuery.memberOfGroup(request.getGroupId());
		}
		if (StringUtils.isNotEmpty(request.getProcDefId())) {
			userQuery.potentialStarter(request.getProcDefId());
		}
		long total = userQuery.count();
		List<User> userList = userQuery.orderByUserId().asc().listPage(request.firstRowNum(), request.getPageSize());
		Leaf<User> leaf = new Leaf<>(userList, total, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult saveGroup(Group group) {
		identityService.saveGroup(group);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deleteGroup(Group group) {
		identityService.deleteGroup(group.getId());
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<Group> getGroup(String groupId) {
		Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
		return ResponseResult.success(group);
	}

	@Override
	public ResponseResult<Leaf<Group>> getGroupList(GroupQueryRequest request) {
		GroupQuery groupQuery = identityService.createGroupQuery();
		if (StringUtils.isNotEmpty(request.getId())) {
			groupQuery.groupId(request.getId());
		}
		if (StringUtils.isNotEmpty(request.getName())) {
			groupQuery.groupName(request.getName());
		}
		if (StringUtils.isNotEmpty(request.getNameLike())) {
			groupQuery.groupNameLike(request.getNameLike());
		}
		if (StringUtils.isNotEmpty(request.getType())) {
			groupQuery.groupType(request.getType());
		}
		if (StringUtils.isNotEmpty(request.getProcDefId())) {
			groupQuery.potentialStarter(request.getProcDefId());
		}
		long total = groupQuery.count();
		List<Group> groupList = groupQuery.orderByGroupId().asc().listPage(request.firstRowNum(), request.getPageSize());
		Leaf<Group> leaf = new Leaf<>(groupList, total, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult saveUserGroupMemership(String userId, String groupId) {
		identityService.createMembership(userId, groupId);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult saveUserAndGroup(SaveUserAndGroupRequest request) {
		User user = request.getUser();
		Group group = request.getGroup();
		saveUser(user);
		saveGroup(group);
		saveUserGroupMemership(user.getId(), group.getId());
		return ResponseResult.success();
	}

}
