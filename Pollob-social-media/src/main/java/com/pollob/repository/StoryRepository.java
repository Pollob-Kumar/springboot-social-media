package com.pollob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pollob.models.Story;

public interface StoryRepository extends JpaRepository<Story, Integer> {
	
	public List<Story> findByUserId(Integer userId);

}
