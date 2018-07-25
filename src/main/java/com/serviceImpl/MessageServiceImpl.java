package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Message;
import com.entity.Query;
import com.mapper.MessageMapper;
import com.serviceInterface.MessageServiceInterface;
@Service
public class MessageServiceImpl implements MessageServiceInterface {

	@Autowired
	private MessageMapper mmp;
	
	@Override
	public List<Message> getMessage(Query q) {
		if(q==null){
			return null;
		}
		if(q.getPage()<0||q.getLimit()<=0){
			return null;
		}
		
		return mmp.getMessage(q);
	}

	public int countAll() {
		return mmp.countAll();
	}

}
