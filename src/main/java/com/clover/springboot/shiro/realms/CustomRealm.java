package com.clover.springboot.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.clover.springboot.pojo.Permission;
import com.clover.springboot.pojo.Role;
import com.clover.springboot.pojo.User;
import com.clover.springboot.service.impl.UserService;

public class CustomRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		User user = userService.getUserByName(userName);

		// 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (Role role : user.getRoleList()) {
			// 添加角色
			simpleAuthorizationInfo.addRole(role.getRoleName());
			// 添加权限
			for (Permission permissions : role.getPmList()) {
				simpleAuthorizationInfo.addStringPermission(permissions.getPmName());
			}
		}
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (StringUtils.isEmpty(token.getPrincipal())) {
			return null;
		}
		// 获取用户信息
		String name = token.getPrincipal().toString();
		User user = userService.getUserByName(name);
		if (user == null) {
			return null;
		} else {
			// 这里验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
			return simpleAuthenticationInfo;
		}
	}
}
