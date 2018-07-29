package com.serviceInterface;

import java.util.List;

import com.entity.Query;
import com.entity.WeiBo;
import com.entity.WeiboAndUser;
import com.github.pagehelper.PageInfo;

public interface WeiBoServiceInterface {

	public List<WeiBo> selectAll();
	public List<WeiBo> selectPage(Query q);
	public List<WeiBo> findByAccount(String account);
	public int countAll();
	public int countUserWeibo(int userId);
	public boolean deleteWeiBoById(int id);
	public List<WeiboAndUser> selectToUserPage(Query q);
	public boolean updateWeiBo(WeiBo weibo);
	public boolean insertWeiBo(WeiBo weibo);
	public Integer getFocus(Query q);
	public Integer getBefocus(Query q);
	public PageInfo selectWeiboByDate(Integer day,Query q) ;
}
