package com.fallframework.platform.starter.shiro.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * 生成accesstoken
 *
 * @author zhuangpf
 */
public class JWTIdGenerator implements SessionIdGenerator {

	@Override
	public Serializable generateId(Session session) {
		System.out.println(session);
		return null;
	}

}
