package com.pollob.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pollob.models.User;

/*
 * User class er jonno ei UserController.
 * Ei vabei API ke Data sent kora hoy, mane ei vabei database a data jabe
 * User class er (ja models er majhe ache) real data diye test/ kora
 */
@RestController
public class UserController {

	/*
	 * database theke data read korar jonno @GetMapping and "/home" holo endpoint.
	 * "List" use korchi jate data list akare print hoy, and <User> eta holo generic mane Return type User hobe.
	 */
	@GetMapping("/users")
	public List<User> getUser() {
		
		//ArrayList use korchi jate extra jaiga na ney.
		List<User> users=new ArrayList<>();
		
		// API/database k ei data sent kora holo
		User user1=new User("Pks","Pollob","pks@gmail.com","123");
		User user2=new User("2Pks","Pollob","pks@gmail.com","123");
		
		//user1 obj er majhe je data gula ache ta add kore dilam users obj a. jate user er data gulo list akare print hoy.
		users.add(user1);
		users.add(user2);
		
		//users return korlam karon akhane sob user er data store hoye ache.
		return  users;
	}
}