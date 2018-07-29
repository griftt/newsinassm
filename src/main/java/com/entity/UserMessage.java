package com.entity;

import java.util.List;

public class UserMessage {
	private List<Friend> friendList;
	private User user;
	private List<WeiBo> weiboList;
	private String  province;
	private Integer weiboCount;
	private Integer focusCount;
	private Integer befocusCount;
	

	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Integer getBefocusCount() {
		return befocusCount;
	}
	public void setBefocusCount(Integer befocusCount) {
		this.befocusCount = befocusCount;
	}
	public Integer getWeiboCount() {
		return weiboCount;
	}
	public void setWeiboCount(Integer weiboCount) {
		this.weiboCount = weiboCount;
	}
	public Integer getFocusCount() {
		return focusCount;
	}
	
	public List<Friend> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<WeiBo> getWeiboList() {
		return weiboList;
	}
	public void setWeiboList(List<WeiBo> weiboList) {
		this.weiboList = weiboList;
	}
	@Override
	public String toString() {
		return "UserMessage [friendList=" + friendList + ", user=" + user + ", weiboList=" + weiboList + ", province="
				+ province + ", weiboCount=" + weiboCount + ", focusCount=" + focusCount + ", befocusCount="
				+ befocusCount + "]";
	}
	public void setFocusCount(Integer focusCount) {
		this.focusCount = focusCount;
	}
	
}