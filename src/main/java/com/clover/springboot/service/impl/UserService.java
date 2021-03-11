package com.clover.springboot.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clover.springboot.pojo.Permission;
import com.clover.springboot.pojo.Role;
import com.clover.springboot.pojo.User;

@Service
public class UserService {
	public User getUserByName(String getMapByName) {
		return getMapByName(getMapByName);
	}

	/**
	 * 模拟数据库查询
	 *
	 * @param userName
	 *            用户名
	 * @return User
	 */
	private User getMapByName(String userName) {
		// 角色权限
		List<Permission> permissionsList = new ArrayList<>();
		Permission permissions1 = new Permission("1", "query");
		Permission permissions2 = new Permission("2", "add");
		permissionsList.add(permissions1);
		permissionsList.add(permissions2);

		// 用户角色
		List<Role> roleList = new ArrayList<>();
		Role role = new Role("1", "admin", permissionsList);
		roleList.add(role);

		User user = new User("clover", "123456", "1", roleList);
		Map<String, User> map = new HashMap<>();
		map.put(user.getName(), user);

		// 模拟用户2
		List<Permission> permissionsList2 = new ArrayList<>();
		permissionsList2.add(permissions1);
		Role role1 = new Role("2", "user", permissionsList2);
		List<Role> roleSet1 = new ArrayList<>();
		roleSet1.add(role1);
		User user1 = new User("zhang", "123456", "2", roleSet1);
		map.put(user1.getName(), user1);

		return map.get(userName);
	}
}
