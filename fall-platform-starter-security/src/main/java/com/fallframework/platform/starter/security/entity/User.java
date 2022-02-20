package com.fallframework.platform.starter.security.entity;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import com.fallframework.platform.starter.rbac.model.SexEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * UserDetails security 标准接口
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 6865038222333922080L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户昵称
	 */
	private String username;

	/**
	 * 头像URL
	 */
	private String avtar;

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
	 * 生日
	 */
	private Date birthday;

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

	/**
	 * 资源权限
	 */
	private List<GrantedAuthority> authorities;

	/**
	 * @return 当前账户所具有的所有角色编码信息
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @return 用户名
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @return 当前账户凭证{密码}是否过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * @return 当前账户是否被锁
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * @return 当前账户密码是否过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * @return 当前账户是否可用
	 */
	@Override
	public boolean isEnabled() {
		return StatusEnum.ENABLE.equals(status) ? true : false;
	}

}