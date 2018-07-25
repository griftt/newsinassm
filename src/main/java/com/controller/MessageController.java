package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.ResultMap;
import com.entity.Message;
import com.entity.Query;
import com.serviceImpl.MessageServiceImpl;

@RequestMapping("/message")
@Controller
public class MessageController {
	
	@Resource(name="messageServiceImpl")
	private MessageServiceImpl msi;
	
	@RequestMapping("/userMessage.action")
	@ResponseBody
	public ResultMap getMessage(Integer page,Integer limit){
		System.err.println(page+"messagecontroller"+limit);
		if(page==null||limit==null){
			return null; 
		}
		if(page<=0||limit<=0){
			return null;
		}
		List<Message> list=new ArrayList<>();
		int count=msi.countAll();	
		list=msi.getMessage(new Query((page-1)*limit,limit));
		System.err.println(list);
		return new ResultMap(0,"", list, count); 
	}
	
}
