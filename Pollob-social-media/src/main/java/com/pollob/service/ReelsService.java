package com.pollob.service;



import java.util.List;

import com.pollob.models.Reels;
import com.pollob.models.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel, User user);
	
	public List<Reels> fingAllReels();
	
	public List<Reels> findUsersReel(Integer userId) throws Exception;

}
