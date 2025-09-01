package com.pollob.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pollob.models.User;
import com.pollob.repository.UserRepository;
import com.pollob.service.UserService;

/*
 * User class er jonno ei UserController.
 * Ei vabei API ke Data sent kora hoy, mane ei vabei database a data jabe
 * User class er (ja models er majhe ache) real data diye test/ kora
 */
@RestController
public class UserController {
	
	/*
	 * "UserRepository" k import korlam "UserController" class a (import korlam karon "JpaRepository" er sokol kichu ache "UserRepository" er majhe) and tar object create korlam "userRepository".
	 *  @Autowired= auto connection
	 */
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	/*database jonno 4-V te opore niye aslam, jekono jaigate rakha jai ata
	 * @PostMapping= jokhon database a data add kora lagbe tokhon "@PostMapping" use hoy.
	 * @RequestBody= jokhon kono frontend libray(ex. POSTMAN) theke data sent kori, tokhon data body-te sent korbo, ja database-a add hobe.
	 * akhane(@RequestBody User user) "User" holo datatype and "user" holo obj/variable.
	 * data insert er jonno ei "@PostMapping"
	 */
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		

		//database a data save korar jonno "save" method ja ache "userRepository" er majhe.
		User savedUser=userService.registerUser(user);

		//karon "savedUser" obj a save data ache.
		return savedUser;
		
	}
	

	/*
	 * database theke data read korar jonno @GetMapping and "/home" holo endpoint.
	 * "List" use korchi jate data list akare print hoy, and <User> eta holo generic mane Return type User hobe.
	 */
	
	/*
	 * sob user get korar jonno
	 * "List<User>" sob user er data list akare jeno ase sei jonno list use hoyeche..
	 */
	@GetMapping("/users")
	public List<User> getUser() {
		
		List<User> users=userRepository.findAll();

		return  users;
	}
	
	
	//user id("{userId}") diye, user find kora.
	/*
	 * "findById(Id)" user id diye user find kora.
	 * Optional= User ache or nai bujhai optional. jokhon input diya id database a thakbe na, tai ata use kora hoyeche
	 * 
	 */
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer Id) throws Exception {
		
		User user=userService.findUserById(Id);
		
		return user;
	}
	
	
	/*
	 * "@PutMapping" use hoy data update korar jonno.
	 * @RequestBody body te sei data dibo jei data change/update korte cai.
	 */
	
	@PutMapping("/users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
		
		User updatedUser=userService.updateUser(user, userId);
		
		return updatedUser;
	}
	
	@PutMapping("/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
	
	User user=userService.followUser(userId1, userId2);
	return user;
	}
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users=userService.searchUser(query);
		
		return users;
	}
	 
	
}