package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Friend;
import com.entity.Query;
import com.mapper.FriendMapper;
import com.serviceInterface.FriendServiceInterface;

@Service 
public class FriendServiceImpl implements FriendServiceInterface{

	@Autowired
	private FriendMapper frm;
	
	@Override
	public List<Friend> selectAll() {
		return null;
	}

	@Override
	public List<Friend> selectPage(Query q) {
		if(q==null){
			return null;
		}
		if(q.getUserId()<=0||q.getLimit()<=0||q.getPage()<0){
			return null;
		}
		return frm.selectPage(q);
	}

	@Override
	public int countAll() {
		return 0;
	}

	@Override
	public List<Friend> findByuserId(int userId) {
		return null;
	}

	@Override
	public int countUserFriend(int objectId) {
		if(objectId<=0){
			return 0;
		}
		return frm.countUserFriend(objectId);
	}

	@Override
	public void deleteUserFriend(int id) {
		
	}

	@Override
	public void updateUserFriend(Friend fri) {
		
	}

	@Override
	public void insertUserFriend(Friend fri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canleFocus(Query q) {
		// TODO Auto-generated method stub
		if(q==null){
			return false;
			}
			if(q.getObjectId()<=0||q.getUserId()<=0){
				return false;
			}
			boolean y=  frm.canleFocus(q);
			if(y){
				return frm.canleBeFocus(q);
			}
			return false;
		
	}

	@Override
	public boolean canleBeFocus(Query q) {
		if(q==null){
		return false;
		}
		if(q.getObjectId()<=0||q.getUserId()<=0){
			return false;
		}
		boolean y=  frm.canleFocus(q);
		if(y){
			return frm.canleBeFocus(q);
		}
		return false;
	}

	
}
