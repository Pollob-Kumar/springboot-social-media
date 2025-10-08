package com.pollob.service; 

import java.util.List;
import com.pollob.models.Post;

/*
 * ei interface ta service layer er jonno banano hoyeche
 *ekhane post related sob operation er method declare kora ache
 *ekhane sokol business logic(method) thakbe.
 */
public interface PostService {

	/*
	 * Post: eta hocche method er return type.
	 * createNewPost: eta hocche method er nam. eta new ekti Post create korar jonno use hobe.
	 * (Post post, Integer userId): 2ta parameter. 
	 *      Post post: new je Post ta create korte cai, tar data(Ex. title, content e.t.c) ei obj a thakbe.
	 *      Integer userId: je user Post ta create korche, tar id.
	 * throws Exception: method er majhe kono problem hole seta Exception akare Throw kore. "try catch" diye problem dhorte hob.
	 */
	Post createNewPost(Post post, Integer userId) throws Exception;
	
	/*
	 * ei method ta ekta post delete korar jonno
	 * postId holo kon post ta delete korte hobe ar userId holo je user delete korche
	 * return korbe ekta String message (jemon "Post deleted successfully")
	 * error hole Exception throw korbe
	 */
	String deletePost(Integer postId, Integer userId) throws Exception;
	
	/*
	 * ei method ta userId diye specific user er sob post ber korar jonno 
	 * userId diye je user ke khuja hobe, tar sob post List akare return korbe
	 */
	List<Post> findPostByUserId(Integer userId);
	
	/*
	 * ei method ta postId diye ekta specific post khuje ber korar jonno use hoy
	 * postId diye je post khuje pabe seta return korbe Post object hishebe
	 *  jodi post na paoa jay, tahole Exception throw korbe
	 */
	Post findPostById(Integer postId) throws Exception;
	
	/*
	 * ei method ta sob post (joto post ache sob) return korbe List akare
	 */
	List<Post> findAllPost();
	
	/*
	 * ei method ta ekta post "save" korar jonno (mane user jodi post ta save korte chay)
	 * postId holo kon post ta save korbe, ar userId holo je user save korche
	 * return korbe saved Post object
	 * error hole Exception throw korbe
	 */
	Post savedPost(Integer postId, Integer userId) throws Exception;
	
	/*
	 * ei method ta ekta post "like" korar jonno
	 * postId holo kon post ta like korte hobe, ar userId holo je user like korche
	 * return korbe updated Post object (Ex. like count barbe)
	 * error hole Exception throw korbe
	 */
	Post likePost(Integer postId, Integer userId) throws Exception;
}