package com.mapper;

import java.util.List;

import com.entity.Friend;
import com.entity.Query;
import com.entity.User;
import com.entity.Uw;

public interface UserMapper {
	public void deleteUserById(int id);
	public User selectById(int id);
	public List<User> selectPage(Query q);
	public void updateUser(User user);
	public void insertUser(User user);
	public int countAll();
	public List<User> findByAccount(String account);
	public User userLogin(User user);
	public List<Friend> findUserFriend(int id);
	public List<User> selectNewUserByDate(Integer day);
	public List<User> selectUserOnline(Query q);
	public List<Uw> selectUserWeiboByDate(Query q);
}
