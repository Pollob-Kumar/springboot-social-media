package com.pollob.controller;

import java.util.ArrayList;
import java.util.List;

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
	 * 
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
	@GetMapping("/users")
	public List<User> getUser() {
		
		//ArrayList use korchi jate extra jaiga na ney.
		List<User> users=new ArrayList<>();
		
		// API/database k ei data sent kora holo
		User user1=new User(1,"Pks","Pollob","pks@gmail.com","123");
		User user2=new User(2,"2Pks","Pollob","pks@gmail.com","123");
		
		//user1 obj er majhe je data gula ache ta add kore dilam users obj a. jate user er data gulo list akare print hoy.
		users.add(user1);
		users.add(user2);
		
		//users return korlam karon akhane sob user er data store hoye ache.
		return  users;
	}
	
	//user id("{userId}") diye, user find kora.
	@GetMapping("/users/{userId}")
	
	/*
	 * user id("{userId}") access korar jonno "@PathVariable" nichi
	 * jodi endpoint("{userId}") & Integer("Id") er porer obj, 2tai same thake tahole "@PathVariable" er parameter-er majhe kichui define kora lagbe na.
	 * jodi endpoint("{userId}") & Integer("Id") er porer obj, 2tai same na thake tahole "@PathVariable" er parameter-er majhe define kora lagbe("@PathVariable("userId")") je, Integer-er pore obj ta endpoint er obj. mane endpoint & parameter same thakte hobe.
	 * sudu User nilam("List<User>" nilam na) karon id jonno ektai user hobe.
	 */
	public User getUserById(@PathVariable("userId") Integer Id) {
		
		User user1=new User(1,"Pks","Pollob","pks@gmail.com","123");
		user1.setId(Id);
		
		return  user1;
	}
	
	/*
	 * "@PutMapping" use hoy data update korar jonno.
	 * @RequestBody body te sei data dibo jei data change/update korte cai.
	 */
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		
		//change korar jonno ei data dilam.
		User user1=new User(1,"Pks","Pollob","pks@gmail.com","123");
		
		//jodi "FirstName"(mane null thakbe na) age theke thake tahole ei condition kaj hobe.
		if(user.getFirstName()!=null) {
			user1.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			user1.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			user1.setEmail(user.getEmail());
		}
		
		//ei vabe sob gula
		
		return user1;
	}
	
	/*
	 * @DeleteMapping= jekono data delete korar jonno use kora hoy.
	 * "deleteUser" method er return type "String/void" dilei hobe. kintu Delete hoyeche ki'na ta janar jonno String use kore ekta messages return korbo.
	 * @PathVariable= use kori endpoint theke ja amra pass kori("{userId}" ei user data delete korte cai) ta access korar jonno.
	 */
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Integer userId) {

		return "user deleted successfully with id "+userId;
	//note: Get method & Delete method body accept kore na(POSTMAN a)
	}
	 
	
}