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
import org.springframework.web.bind.annotation.RestController;

import com.pollob.models.User;
import com.pollob.repository.UserRepository;

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
	
	/*database jonno 4-V te opore niye aslam, jekono jaigate rakha jai ata
	 * @PostMapping= jokhon database a data add kora lagbe tokhon "@PostMapping" use hoy.
	 * @RequestBody= jokhon kono frontend libray(ex. POSTMAN) theke data sent kori, tokhon data body-te sent korbo, ja database-a add hobe.
	 * akhane(@RequestBody User user) "User" holo datatype and "user" holo obj/variable.
	 * data insert er jonno ei "@PostMapping"
	 */
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		User newUser=new User();
		
		/*
		 * user.getEmail()= "user" obj theke(ja "@RequestBody" method er parameter-a  "User" class er obj kore nichilam.) email ber kore ane( getter method diye, ja User.java class a define kora ache.) 
		 * newUser.setEmail(...)= ber kora email "newUser" obj-er Email a bosiye dey(setter method diye, ja User.java class a define kora ache)
		 */
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());

		//database a data save korar jonno "save" method ja ache "userRepository" er majhe.
		User savedUser=userRepository.save(newUser);

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
		
		Optional<User> user= userRepository.findById(Id);
		
		//jodi user thake
		if(user.isPresent()) {
			return user.get();
		}
		//jodi id na paoya jai. tokhon ei exception asbe
		throw new Exception("user not exist with userId "+Id);
	}
	
	
	/*
	 * "@PutMapping" use hoy data update korar jonno.
	 * @RequestBody body te sei data dibo jei data change/update korte cai.
	 */
	
	/*
	 * 
	 */
	@PutMapping("/users/{Id}")
	public User updateUser(@RequestBody User user, @PathVariable Integer Id) throws Exception {
		
		Optional<User> user1=userRepository.findById(Id);
		
		//jodi empty hoy tahole kaj korbe
		if(user1.isEmpty()) {
			throw new Exception("user not exit with id "+Id);
		}
		
		
		 User oldUser=user1.get();
		 
		//jodi user na thake oi id diye 
		 if(user.getFirstName()!=null) {
			 oldUser.setFirstName(user.getFirstName());
		 }
		 if(user.getLastName()!=null) {
			 oldUser.setLastName(user.getLastName());
		 }
		 //ei vabei sob hobe.
		
		 //note: "save"= use hobe data add and update korte
		 User updatedUser=userRepository.save(oldUser);
		
		 return updatedUser;
	}
	
	/*
	 * @DeleteMapping= jekono data delete korar jonno use kora hoy.
	 * "deleteUser" method er return type "String/void" dilei hobe. kintu Delete hoyeche ki'na ta janar jonno String use kore ekta messages return korbo.
	 * @PathVariable= use kori endpoint theke ja amra pass kori("{userId}" ei user data delete korte cai) ta access korar jonno.
	 */
	
	/*
	 * 
	 */
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {
		
		//check kore user ache ki na
        Optional<User> user1=userRepository.findById(userId);
		
        //jodi user empty hoy
		if(user1.isEmpty()) {
			throw new Exception("user not exit with id "+userId);
		}
		
		userRepository.delete(user1.get());
		
		return "user deleted successfully with id "+userId;
		
	}
	 
}