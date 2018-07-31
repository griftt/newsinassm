package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.entity.Message;
import com.entity.Query;
import com.mapper.MessageMapper;
import com.serviceInterface.MessageServiceInterface;
@Service
public class MessageServiceImpl implements MessageServiceInterface {

	@Autowired
	private MessageMapper mmp;
	@Resource(name="redisTemplate")
	private RedisTemplate rt;
	@Override
	public List<Message> getMessage(Query q) {
		if(q==null){
			return null;
		}
		if(q.getPage()<0||q.getLimit()<=0){
			return null;
		}
		if(rt.opsForValue().get("comment")==null){
			 List<Message>msg=mmp.getMessage(q);
			 rt.opsForValue().set("comment", msg);
			 return msg;
		}
		List<Message>msg=  (List<Message>) rt.opsForValue().get("comment");
		System.err.println("msg:"+msg);
		return msg;
	}

	public int countAll() {
		return mmp.countAll();
	}

}
