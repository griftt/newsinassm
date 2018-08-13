package com.serviceImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
		ValueOperations value = rt.opsForValue();
		if(value.get("comment")==null){
			System.out.println("还没有缓存");
			 List<Message>msg=mmp.getMessage(q);
			 
			 value.set("comment", msg,10,TimeUnit.SECONDS);
			 
			 return msg;
		}
		List<Message>msg=(List<Message>)value.get("comment");
		System.err.println(rt.getExpire("comment")+"msg:"+msg);
		return msg;
	}

	public int countAll() {
		return mmp.countAll();
	}

}
