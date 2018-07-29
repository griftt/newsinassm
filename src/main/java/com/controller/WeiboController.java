package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.data.ResultMap;
import com.entity.Friend;
import com.entity.Query;
import com.entity.User;
import com.entity.UserMessage;
import com.entity.WeiBo;
import com.entity.WeiboAndUser;
import com.github.pagehelper.PageInfo;
import com.serviceImpl.FriendServiceImpl;
import com.serviceImpl.UserServiceImpl;
import com.serviceImpl.WeiBoServiceImpl;

@Controller
@RequestMapping("/weibo")
public class WeiboController {
	@Resource(name="weiBoServiceImpl")
	private WeiBoServiceImpl wbs;
	@Resource(name="friendServiceImpl")
	private FriendServiceImpl fsi;
	@Resource(name="userServiceImpl")
	private UserServiceImpl us;
	
	
	//后台请求的数据 用户的微博信息
	@RequestMapping("/weiboPage.action")
	@ResponseBody
	public ResultMap seletePage(Integer page,Integer limit,@RequestParam("id") Integer userId){
		System.out.println(page+"'"+limit+"'"+userId);
		if(page==null||limit==null||userId==null){
			return null;
		}
		if(page<=0||limit<=0||userId<=0){
			return null;
		}
		Query query = new Query();
		query.setUserId(userId);
		query.setLimit(limit);
		query.setPage((page-1)*limit);
		List<WeiBo> list = wbs.selectPage(query);
		int count=wbs.countUserWeibo(userId);
		if(list.isEmpty()){
			return new ResultMap(0,"内容为空",list,300);
		}
		
		return new ResultMap(0,"内容为空",list,count);
		
	}
	
	

		//获取最近几天的微博
	@RequestMapping("/selectWeiBoByDate.action")
	@ResponseBody
	public ResultMap selectWeiboByDate(Integer day,Integer page,Integer limit){
		if(day==null||day<0||page<=0||limit<=0){
			return null;
		}
		System.err.println(day);
		Query q = new Query();
		q.setLimit(limit);
		q.setPage(page);
		PageInfo<WeiBo> pageInfo = wbs.selectWeiboByDate(day, q);
		int count =(int) pageInfo.getTotal();
		List<WeiBo> list = pageInfo.getList();
		System.out.println(list);
		if(list.size()==0){
			return new ResultMap(0,"无相关数据", list,0);
		}
		return new ResultMap(0,"", list, count);
		
		
		
	}
	
	
	
}
