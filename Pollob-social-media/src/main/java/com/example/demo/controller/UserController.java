package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;



@RestController
public class UserController {

	@GetMapping("/users")
	public List<User> getUser() {
		
		List<User> users=new ArrayList<>();
		
		User user1=new User("Pks","Pollob","pks@gmail.com","123");
		User user2=new User("2Pks","Pollob","pks@gmail.com","123");
		users.add(user1);
		users.add(user2);
		
		return  users;
	}
}
