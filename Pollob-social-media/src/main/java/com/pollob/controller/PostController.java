package com.pollob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // HTTP status code use korar jonno
import org.springframework.http.ResponseEntity; // HTTP response return korar jonno
import org.springframework.web.bind.annotation.DeleteMapping;// DELETE request handle korar jonno
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;  // request body theke data receive korar 
import org.springframework.web.bind.annotation.RestController; // ei annotation bole je ei class ta REST API controller

import com.pollob.models.Post;
import com.pollob.response.ApiResponse;
import com.pollob.service.PostService;

@RestController
public class PostController {
	
	// PostService automatic inject hobe
	@Autowired
	PostService postService;

	/*
	 *  API: POST -> create new post
	 *  ei method ta ekta notun post create korar jonno use hoy
	 */
	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception{
		
		// service layer e createNewPost() call kora hocche
		Post createdPost=postService.createNewPost(post, userId);
		
		// created post return kora hocche status ACCEPTED (202) diye
		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
	}
	
	/*
	 *  API: DELETE -> delete a post
	 *  ei method ta post delete korar jonno
	 */
	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		// service theke deletePost call kore message pawa hocche
		String message=postService.deletePost(postId, userId);
		
		// ApiResponse object banano hocche success message diye
		ApiResponse res=new ApiResponse(message,true);
	
		// response return kora hocche status OK (200) diye
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		
		Post post=postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	/*
	 * API: GET -> find all posts of a specific user
	 *  ei method ta All post ber korar jonno
	 */
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>>findUserPost(@PathVariable Integer userId){
		
		// specific user er sob post pawa hocche
		List<Post> posts=postService.findPostByUserId(userId);
		
		// list of posts return kora hocche OK (200) diye
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	/*
	 * API: GET -> find all posts (sobar post)
	 * ei method ta sobar post ber korar jonno
	 */
	@GetMapping("/posts")
	public ResponseEntity<List<Post>>findAllPost(){
		
		// sob post fetch kora hocche
		List<Post> posts=postService.findAllPost();
		
		// sob post list return kora hocche OK (200) diye
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	/*
	 * API: PUT -> save or unsave a post
	 * ei method ta post save/un-save korar jonno
	 */
	@PutMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<Post> savePostdHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		// service theke post save/unsave kora hocche
		Post post=postService.savedPost(postId, userId);
		
		// updated post return kora hocche ACCEPTED (202) diye
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	/*
	 * API: PUT -> like or unlike a post
	 * ei method ta post like/unlike korar jonno
	 */
	@PutMapping("/posts/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostdHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		// postService theke like/unlike operation kora hocche
		Post post=postService.likePost(postId, userId);
		
		// updated post return kora hocche ACCEPTED (202) diye
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
}


