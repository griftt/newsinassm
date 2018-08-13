package com.entity;

import java.util.List;

public class Query {
		Integer page;
		Integer limit;
		Integer id;
		String account;
		Integer userId;
		List<?> list;
		Integer objectId;
		String message;
		
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Integer getObjectId() {
			return objectId;
		}
		public void setObjectId(Integer objectId) {
			this.objectId = objectId;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getPage() {
			return page;
		}
		public void setPage(Integer page) {
			this.page = page;
		}
		public Integer getLimit() {
			return limit;
		}
		public void setLimit(Integer limit) {
			this.limit = limit;
		}
		public Query(int page,int limit){
			this.page=page;
			this.limit=limit;
		}
		public Query(){
			
		}
		
		
		public List<?> getList() {
			return list;
		}
		public void setList(List<?> list) {
			this.list = list;
		}
		@Override
		public String toString() {
			return "Query [page=" + page + ", limit=" + limit + ", id=" + id + ", account=" + account + ", userId="
					+ userId + ", list=" + list + ", objectId=" + objectId + "]";
		}
		
		
		
	
}
