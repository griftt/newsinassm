package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.Friend;
import com.entity.Query;
import com.entity.User;
import com.entity.Uw;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserMapper;
import com.serviceInterface.UserServiceInterface;
@Service
public class UserServiceImpl implements UserServiceInterface {

	@Resource(name="userMapper")
	private UserMapper userMapper;
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectPage(Query q) {
		// TODO Auto-generated method stub
		if(q!=null){
			return userMapper.selectPage(q);
		}
		
		
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return userMapper.countAll();
	}

	@Override
	public List<User> findByAccount(String account) {
		// TODO Auto-generated method stub
		if(account==null || "".equals(account)||!account.startsWith("7")){
			return null;
		}
		return userMapper.findByAccount("%"+account+"%");
	}

	@Override
	public User userLogin(User user) {
		if(user==null){
			return null;
		}
		if(user.getAccount()==null||user.getPwd()==null){
			return null;
		}
		User user2 = userMapper.userLogin(user);
		if(user2==null){
			System.err.println("user2null+aaaaaaaaaaaaaaaaaaaaaaaaa");
			return null;
		}
		return user2;
	}

	@Override
	public List<Friend> findUserFriend(int id) {
		if(id<=0){
			return null;
		}
		return userMapper.findUserFriend(id);
	}

	public PageInfo<User> selectNewUserByDate(Integer day, Query q) {
		PageHelper.startPage(q.getPage(),q.getLimit());
		
		List<User> list = userMapper.selectNewUserByDate(day);
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	public PageInfo<User> selectUserOnline(Query q) {
		PageHelper.startPage(q.getPage(),q.getLimit());
		List<User> list = userMapper.selectUserOnline(q);
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	
		
	}
	
	public List<Uw> s(Query q){
		List<Uw> list = userMapper.selectUserWeiboByDate(q);
		System.err.println(list);
		return list;
	}
	
	
}
