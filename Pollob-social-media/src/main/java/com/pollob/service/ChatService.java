package com.pollob.service;

import java.util.List;

import com.pollob.models.Chat;
import com.pollob.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);

}
