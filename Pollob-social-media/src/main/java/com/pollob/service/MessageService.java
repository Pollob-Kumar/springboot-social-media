package com.pollob.service;

import java.util.List;


import com.pollob.models.Chat;
import com.pollob.models.Message;
import com.pollob.models.User;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	public List<Message> findChatMessages(Integer chatId) throws Exception;
	

}
