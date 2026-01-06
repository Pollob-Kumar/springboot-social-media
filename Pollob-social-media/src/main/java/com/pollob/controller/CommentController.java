package com.pollob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pollob.models.Comment;
import com.pollob.models.User;
import com.pollob.service.CommentService;
import com.pollob.service.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/comments/{postId}")
	public Comment createComment(@RequestBody Comment comment, 
			@RequestHeader("Authorization") String jwt,
			@PathVariable("postId") Integer postId) throws Exception {
		
		User user = userService.finddUserByJwt(jwt);
		
		Comment createdComment = commentService.createComment(comment, 
				postId, 
				user.getId());
		
		return createdComment;
	}
	
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(
			@RequestHeader("Authorization") String jwt,
			@PathVariable("commentId") Integer commentId) throws Exception {
		
		User user = userService.finddUserByJwt(jwt);
		
		Comment likeComment = commentService.likeComment(commentId, user.getId());
		
		return likeComment;
	}

}
