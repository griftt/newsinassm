package com.entity;

import java.io.Serializable;

public class Admin implements Serializable{
	private int id;
	private String name;
	private String pic;
	private String account;
	private String pwd;
	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", pic=" + pic + ", account=" + account + ", pwd=" + pwd
				+ ", roleId=" + roleId + "]";
	}

	

}
