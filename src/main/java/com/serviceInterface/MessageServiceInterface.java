package com.serviceInterface;

import java.util.List;

import com.entity.Message;
import com.entity.Query;

public interface MessageServiceInterface {
	public List<Message> getMessage(Query q);
	public int countAll();
}
