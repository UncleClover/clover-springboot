package com.clover.springboot.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 * 
 * @author zhangdq
 * @time 2018年3月14日 下午4:43:08
 * @Email qiang900714@126.com
 */
public class User implements Serializable {
	private String id;
	private String name;
	private String sex;
	private String addr;
	private int age;
	private String password;
	private List<Role> roleList;

	public User(String name, String password, String sex, List<Role> roleList) {
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.roleList = roleList;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
