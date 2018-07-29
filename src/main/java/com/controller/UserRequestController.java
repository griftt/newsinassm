package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Friend;
import com.entity.Query;
import com.entity.User;
import com.entity.UserMessage;
import com.entity.WeiBo;
import com.entity.WeiboAndUser;
import com.serviceImpl.FriendServiceImpl;
import com.serviceImpl.UserServiceImpl;
import com.serviceImpl.WeiBoServiceImpl;
@Controller
@RequestMapping("/userRequest")
public class UserRequestController {
	
	@Resource(name="weiBoServiceImpl")
	private WeiBoServiceImpl wbs;
	@Resource(name="friendServiceImpl")
	private FriendServiceImpl fsi;
	@Resource(name="userServiceImpl")
	private UserServiceImpl us;

	@RequestMapping("/userMessage.action")
	@ResponseBody
	public UserMessage getUserMessage(Integer id,Integer objectId){
		System.out.println(id+"kkkkkkkkkkkkkkkkkkkkkkkkk"+objectId);
		if(id==null||objectId==null){
			return null;
		}
		if(id<=0||objectId<=0){
			return null;
		}
		
		Query q = new Query();
		q.setUserId(id);
		q.setObjectId(objectId);
		UserMessage um = wbs.getUserMessage(q);
		int focus=wbs.getFocus(q);
		int befocus=wbs.getBefocus(q);
		um.setBefocusCount(befocus);
		um.setFocusCount(focus);
		return um;
			
		}
	//加载用户微博
		@RequestMapping("/weibos2.action")
		public String  getUserWeibo(HttpSession session,Model model){
			Query q = new Query();
			User user = (User)session.getAttribute("user");
			System.err.println("user:"+user);
			q.setId(user.getId());
			List<Friend> fris=us.findUserFriend(user.getId());
			ArrayList<Integer> list = new ArrayList<>();
			if(fris!=null){
				for(Friend f:fris){
					list.add(f.getFriendId());
				}
			}
			list.add(user.getId());
			q.setList(list);
			List<WeiboAndUser> weibos  = wbs.selectToUserPage(q);
			model.addAttribute("weibos", weibos);
			return "sinamain";
		}
		//发布微博
		@RequestMapping("/weiboPublish.action")
		public String weiboPublish(WeiBo weibo,Model model,MultipartFile userpic,HttpSession session) throws IllegalStateException, IOException{
			System.err.println(weibo+","+userpic);
			
			if(weibo==null||userpic==null){
				return "sinamain";
			}
			if((weibo.getContent()==null||"".equals(weibo.getContent()))&&"".equals(userpic.getOriginalFilename())){
				return "sinamain";
			}
			WeiBo w2 = new WeiBo();
			if(!"".equals(userpic.getOriginalFilename())){
				String filename=userpic.getOriginalFilename();
				String uuid=UUID.randomUUID().toString();
				String dirname=uuid.substring(0, 3);
				String newname=uuid+filename.substring(filename.lastIndexOf("."));
				File des = new File("D:/pic/"+dirname+"/"+newname);
				if(!des.exists()){
					des.getAbsoluteFile().mkdirs();
				}
				userpic.transferTo(des);
				w2.setPic(dirname+"/"+newname);
			}
			if(weibo.getContent()!=null||!"".equals(weibo.getContent())){
				w2.setContent(weibo.getContent());
			}
				w2.setTime(new Date());
				w2.setUserId(weibo.getUserId());
				wbs.insertWeiBo(w2);
				model.addAttribute("weibo",w2);
				String path = session.getServletContext().getContextPath();
			return "redirect:/userRequest/weibos2.action";
			
		}
		//取消关注
		@RequestMapping("/canleFocus.action")
		@ResponseBody
		public boolean canleFocus(Integer userId ,Integer objectId){
			System.err.println(userId+"m"+objectId);
			if(userId==null||userId<=0){
				System.err.println("controller");
				return false;
			}
			Query q = new Query();
			q.setObjectId(objectId);
			q.setUserId(userId);
			return fsi.canleFocus(q);
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
		
	
	
	
}
