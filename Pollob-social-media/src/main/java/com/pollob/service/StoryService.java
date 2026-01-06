package com.pollob.service;

import java.util.List;

import com.pollob.models.Story;
import com.pollob.models.User;

public interface StoryService {
	
	public Story createStory (Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
