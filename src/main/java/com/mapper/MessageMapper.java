package com.mapper;

import java.util.List;

import com.entity.Message;
import com.entity.Query;

public interface MessageMapper {
	

	public List<Message> getMessage(Query q);

	public int countAll();
		
		
	
	
}
