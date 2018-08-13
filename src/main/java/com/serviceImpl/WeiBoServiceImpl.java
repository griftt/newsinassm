package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Query;
import com.entity.UserMessage;
import com.entity.WeiBo;
import com.entity.WeiboAndUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.WeiBoMapper;
import com.serviceInterface.WeiBoServiceInterface;

@Service
@Transactional
public class WeiBoServiceImpl implements WeiBoServiceInterface {
	
	@Autowired
	private WeiBoMapper wbm;
	
	@Override
	public List<WeiBo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeiBo> selectPage(Query q) {
		if(q==null){
			System.err.println(1);
			return null;
		}
		if(q.getPage()<0||q.getLimit()<=0){
			System.err.println(2);
			return null;
		}
		return wbm.selectPage(q);
	}
	@Override
	public List<WeiBo> findByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		return wbm.countAll();
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.NEVER)
	public boolean deleteWeiBoById(int id) {
		if(id<=0){
			return false;
		}
		wbm.deleteWeiBoById(id);
		return false;
	}

	@Override
	public boolean updateWeiBo(WeiBo weibo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean insertWeiBo(WeiBo weibo) {
		if(weibo!=null){
			wbm.insertWeiBo(weibo);
		}
		if(weibo.getId()>0){
			return true;
		}
		return false;
	}

	@Override
	public int countUserWeibo(int userId) {
		// TODO Auto-generated method stub
		if(userId<=0){
			return 0;
			
		}
		return 	wbm.countUserWeibo(userId);
	}

	@Override
	public List<WeiboAndUser> selectToUserPage(Query q) {
		if(q!=null&&q.getList().size()>0){
			return wbm.selectToUserPage(q);
		}
		return null;
		
		 
	}

	public UserMessage getUserMessage(Query q) {
		if(q==null){
			return null;
		}
		if(q.getObjectId()<=0||q.getUserId()<=0){
			return null;
		}
		return wbm.getUserMessage(q);
		
	}

	@Override
	public Integer getFocus(Query q) {
		// TODO Auto-generated method stub
		
		return wbm.getFocus(q);
	}

	@Override
	public Integer getBefocus(Query q) {
		// TODO Auto-generated method stub
		return wbm.getBefocus(q);
	}

	@Override
	public PageInfo selectWeiboByDate(Integer day,Query q) {
		if(day<0){
			return null;
		}
		PageHelper.startPage(q.getPage(), q.getLimit());
		List<WeiBo> list=(List<WeiBo>) wbm.selectWeiboByDate(day);
		PageInfo page = new PageInfo(list);
		return page;
	}

	

}
