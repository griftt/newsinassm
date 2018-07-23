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
import com.entity.WeiBo;
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
	@RequestMapping("/weiboPublish.action")
	public String weiboPublish(WeiBo weibo,Model model,MultipartFile userpic) throws IllegalStateException, IOException{
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
		return "redirect:weibos2.action";
		
	}

	@RequestMapping("/weibos.action")
	public String  get(HttpSession session,Model model){
		System.err.println("one");
		Query q = new Query();
		User user = (User)session.getAttribute("user");
		q.setId(user.getId());
		List<WeiBo> weibos = wbs.selectToUserPage(q);
		model.addAttribute("weibos", weibos);
		return "sinamain";
	}

	@RequestMapping("/weibos2.action")
	public String  get2(HttpSession session,Model model){
		Query q = new Query();
		User user = (User)session.getAttribute("user");
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
		List<WeiBo> weibos  = wbs.selectToUserPage(q);
		model.addAttribute("weibos", weibos);
		return "sinamain";
	}

	
	
}
