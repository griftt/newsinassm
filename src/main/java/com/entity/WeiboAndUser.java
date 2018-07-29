package com.entity;

public class WeiboAndUser {
	private WeiBo weibo;
	private User user;
	public WeiBo getWeibo() {
		return weibo;
	}
	public void setWeibo(WeiBo weibo) {
		this.weibo = weibo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "WeiboAndUser [weibo=" + weibo + ", user=" + user + "]";
	}
	
	
	
	
}
