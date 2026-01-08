package com.pollob.service;

import java.util.List;

import com.pollob.exceptions.UserException;
import com.pollob.models.User;

/*
 * User e API(6 ta) interface er majhe nilam, ja method akare prokas korte hoy.
 * Interface, abstraction follow kore. (Interface contract define kore, kintu implementation(ex: UserServiceImplementation) korte hoy jonno class a)
 */
public interface UserService {
	
	//User er jonno 6ta API
	public User registerUser(User user);
	//"UserServiceImplementation" class er "findUserById()" method er sathe "throws Exception" ache. tai akhaneo "throws Exception" dite hoyeche.
	public User findUserById(Integer userId) throws UserException;
	public User findUserByEmail(String email);
	public User followUser(Integer userId1, Integer userId2) throws UserException;
	public User updateUser(User user, Integer userId) throws UserException;
	public List<User> searchUser(String query);
	
	public User finddUserByJwt(String jwt);

}
