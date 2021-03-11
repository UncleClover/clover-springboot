package com.clover.springboot.pojo;

import java.util.List;

public class Role {
	private String roleId;
	private String roleName;
	private List<Permission> pmList;

	public Role(String roleId, String roleName, List<Permission> pmList) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.pmList = pmList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Permission> getPmList() {
		return pmList;
	}

	public void setPmList(List<Permission> pmList) {
		this.pmList = pmList;
	}

}
