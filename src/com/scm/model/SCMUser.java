package com.scm.model;

import java.util.List;
/*用户表*/
public class SCMUser {
	private String account;//用户帐号
	private String password;//密码
	private String name;//姓名
	private String createDate;//添加日期
	private String status;//锁定状态
	/*private List<UserModel> models;//用户职能
	
	public List<UserModel> getModels() {
		return models;
	}
	public void setModels(List<UserModel> models) {
		this.models = models;
	}*/
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	/*public String toString() {
		return "SCMUser [account=" + account + ", password=" + password
				+ ", name=" + name + ", createDate=" + createDate + ", status="
				+ status + ", models=" + models + "]";
	}*/
	
}
