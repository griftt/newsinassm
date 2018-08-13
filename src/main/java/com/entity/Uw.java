package com.entity;

import java.util.Date;

public class Uw {

	private int id;
	private String name;
	private String content;
	private Date time;
	@Override
	public String toString() {
		return "Uw [id=" + id + ", name=" + name + ", content=" + content + ", time=" + time + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
