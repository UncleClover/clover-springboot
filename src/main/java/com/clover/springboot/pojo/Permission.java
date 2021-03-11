package com.clover.springboot.pojo;

public class Permission {
	private String pmId;
	private String pmName;

	public Permission(String pmId, String pmNmae) {
		this.pmId = pmId;
		this.pmName = pmNmae;
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

}
