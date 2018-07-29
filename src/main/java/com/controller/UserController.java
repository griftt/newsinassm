package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.ResultMap;
import com.entity.Query;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name="userServiceImpl")
	private UserServiceImpl us;
	
	@RequestMapping("/loadUser.action")
	@ResponseBody
	public ResultMap loadUser(int page,int limit){
		System.out.println(page+"-----------"+limit);
		int count= us.countAll();
		List<User> users=us.selectPage(new Query(limit*(page-1),limit));
		return new ResultMap(0,"",users,count);
	}
	@RequestMapping("/findUser.action")
	@ResponseBody
	public ResultMap findUser(User user){
		
		 
		System.err.println(user);
		String account =user.getAccount();
		int count= us.countAll();
		List<User> users=null;
		if(account!=null&&!"".equals(account)){
			users=us.findByAccount(account);
		}
		return new ResultMap(0,"",users,count);
	}
	@RequestMapping("/userLogin.action")
	public String login(User user,Model model,HttpSession session ){
		
		if(user==null){
			return "sinalogin";
		}
		if(user.getAccount()==null||user.getPwd()==null){
			return null;
		}
		User user2=us.userLogin(user);
		if(user2!=null){
			System.out.println(user2);
			session.setAttribute("user",user2);
			ServletContext context = session.getServletContext();
			HashMap<Integer, HttpSession> sessionMap = (HashMap<Integer, HttpSession>) context.getAttribute("userSessionMap");
			if(sessionMap==null){
			 sessionMap=new HashMap();
			}else{
				Iterator<Entry<Integer, HttpSession>> ite = sessionMap.entrySet().iterator();
				while(ite.hasNext()){
						if(ite.next().getValue()==null){
							ite.remove();
						};
				}
			}
			sessionMap.put(user2.getId(),session);
			context.setAttribute("userSessionMap",sessionMap);
			return "redirect:/userRequest/weibos2.action";
		}
		return "sinalogin";
	}
	
//新注册用户
	@RequestMapping("/getNewUser.action")
	@ResponseBody
	public ResultMap getNewUser(Integer day,Integer page,Integer limit){
			if(day==null||day<0||page<=0||limit<=0){
				return null;
			}
			System.err.println(day);
			Query q = new Query();
			q.setLimit(limit);
			q.setPage(page);
			PageInfo<User> pageInfo = us.selectNewUserByDate(day, q);
			int count =(int) pageInfo.getTotal();
			List<User> list = pageInfo.getList();
			System.out.println(list);
			if(list.size()==0){
				return new ResultMap(0,"无相关数据", list,0);
			}
			return new ResultMap(0,"", list, count);
		
		
		
	}
	//获取当前在线用户的信息
	@RequestMapping("/getUserOnline.action")
	@ResponseBody
	public ResultMap getUserOnline(Integer page,Integer limit,HttpSession session){
			if(page<=0||limit<=0){
				return null;
			}
			Query q = new Query();
			q.setLimit(limit);
			q.setPage(page);
			HashMap<Integer, HttpSession> map = (HashMap<Integer, HttpSession>) session.getServletContext().getAttribute("userSessionMap");
			if(map==null){
				return new ResultMap(1,"无相关数据",null,0);
			}
			List<Integer> userIdList = new ArrayList(map.keySet());
			System.out.println(userIdList);
			q.setList(userIdList);
			PageInfo<User> pageInfo = us.selectUserOnline(q);
			int count =(int) pageInfo.getTotal();
			List<User> list = pageInfo.getList();
			if(list.size()==0){
				return new ResultMap(0,"无相关数据", list,0);
			}
			return new ResultMap(0,"", list, count);
		
		
		
	}
	
	
}
