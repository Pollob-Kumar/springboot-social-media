package com.pollob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.pollob.models.User;
import com.pollob.repository.UserRepository;

/*
 * "UserServiceImplementation" class a implements(diff= implement) korlam "UserService" Interface k. jate "UserService(I)" a joto un-implement method ache sob access kora jai and segula akhane implement korte pari.
 * API implement korai holo business logic.
 * un-implement method automatically akhane cole asbe, jokhon "UserServiceImplementation" a error dekhabe tokhon class er opore click kore "Add unimplemented method" click korlei sob automatically cole asbe.
 * Ei class, business class logic tai class er opore "@Service" annotation dite hobe.
 */
@Service
public class UserServiceImplementation implements UserService{

	/*
	 * "UserRepository" k import(import korlam karon "JpaRepository" er sokol kichu ache "UserRepository" er majhe) and tar object create korlam "userRepository".
	 *  @Autowired= auto connection
	 */
	@Autowired
	UserRepository userRepository;
	
	/*
	 * "@Override" karon different class but akoi method name and parameters same.
	 * "registerUser" method er body te ja ache, sob "registerUser" method er logic
	 * ei method ta k "UserService" interface use kore, "UserController" class-er "createUser" method theke control korbo.
	 */
	@Override
	public User registerUser(User user) {
		
		//User class er object create korlam
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
	 * "@Override" karon different class but akoi method name and parameters same.
	 * "findUserById" method er body te ja ache, sob "findUserById" method er logic
	 * ei method ta k "UserService" interface use kore, "UserController" class-er "getUserById" method theke control korbo.
	 * throw new Exception() er jonno  "Add throws declaration korte hobe method er sathe. tokhon abar methodName er opor error asbe, tar jonno "Add exception UserService.findUserByeId() --mane InterfaceNme.methodName()" click korlei UserService(I) er findUserById() method er sathe "throws Exception" bosbe.
	 */
	@Override
	public User findUserById(Integer userId) throws Exception {
		
		//check kore user ache ki na
		Optional<User> user= userRepository.findById(userId);
		
		//jodi user thake
		if(user.isPresent()) {
			return user.get();
		}
		//jodi id na paoya jai. tokhon ei exception asbe
		throw new Exception("user not exist with userId "+userId);
	}

	
	/*
	 * "@Override" karon different class but akoi method name and parameters same.
	 * "findUserByEmail" method er body te ja ache, sob "findUserByEmail" method er logic
	 * findByEmail() method predefine na tai amra UserRepository-r vitore(karon UserRepository majhe sokol predefine method thake ex: findById) "findByEmail()" create korbo. mane custom User AIP create korchi. 
	 * custom API create er pore, akhon "userRepository(obj)" dot dilei dekhbo findByEmail() ache.
	 * note: custom API er jonno onek somoy UserController class a kono kaj korte hoy na ex: ei method. and User class a jodi ei field na define kora thake to define korte hobe.
	 * throw new Exception() er jonno  "Add throws declaration korte hobe method er sathe. tokhon abar methodName er opor error asbe, tar jonno "Add exception UserService.findUserByeId() --mane InterfaceNme.methodName()" click korlei UserService(I) er findUserByEmail() method er sathe "throws Exception" bosbe.
	 */
	@Override
	public User findUserByEmail(String email) {
		User user=userRepository.findByEmail(email);
		return user;
	}

	
	/*
	 * "@Override" karon different class but akoi method name and parameters same.
	 * "followUser" method er body te ja ache, sob "followUser" method er logic
	 * UserRepository te followUser() create korbo na karon akhane dorkar ney.
	 * Kintu "UserController" class a ei followUser() ta handle korte hobe. jar  name dichi "followUserHandler" ja ache UserController class a.
	 */
	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		
		/*
		 * je follow korbe
		 * "findUserById()" method use korlam, tai full code lekha laglo na.
		 * "findUserById()" method a error dekhabe, sei jonno "Add throws declaration" dibo. and "followUser()" method a error jonno "Add exceptions to 'UserService.followUser()'" click korbo.
		 */
		User user1=findUserById(userId1);
		
		//jake follow korbe
		User user2=findUserById(userId2);
		
		//user1 user2 k follow korle, user2 update hoye tar follower k add kore dibe. ex: user1 
		user2.getFollowers().add(user1.getId());
		
		//user1 user2 k follow korle, user1 update hoye tar following a add hobe user2.
		user1.getFollowings().add(user2.getId());
		
		//2ta user kei save korlam karon 2ta user e update korchi
		userRepository.save(user1);
		userRepository.save(user2);
		
		//user1 follow korte caichilo tai user1 return korbo
		return user1;
	}

	
	/*
	 * "@Override" karon different class but akoi method name and parameters same.
	 * "updateUser" method er body te ja ache, sob "updateUser" method er logic
	 * ei method ta k "UserService" interface use kore, "UserController" class-er "createUser" method theke control korbo.
	 * throw new Exception() er jonno  "Add throws declaration korte hobe method er sathe. tokhon abar methodName er opor error asbe, tar jonno "Add exception UserService.findUserByeId() --mane InterfaceNme.methodName()" click korlei UserService(I) er updateUser() method er sathe "throws Exception" bosbe.
	 */
	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		
		Optional<User> user1=userRepository.findById(userId);
		
		//jodi empty hoy tahole kaj korbe
		if(user1.isEmpty()) {
			throw new Exception("user not exit with id "+userId);
		}
		
		
		 User oldUser=user1.get();
		 
		//jodi user na thake oi id diye 
		 if(user.getFirstName()!=null) {
			 oldUser.setFirstName(user.getFirstName());
		 }
		 if(user.getLastName()!=null) {
			 oldUser.setLastName(user.getLastName());
		 }
		 if(user.getEmail()!=null) {
			 oldUser.setEmail(user.getEmail());
		 }
		 //ei vabei sob hobe.
		
		 //note: "save"= use hobe data add and update korte
		 User updatedUser=userRepository.save(oldUser);
		
		 return updatedUser;
	}

	
	/*
	 * "@Override" karon different class but akoi method name and parameters same.
	 * "searchUser" method er body te ja ache, sob "searchUser" method er logic
	 * searchUser() method predefine na tai amra UserRepository-r vitore(karon UserRepository majhe sokol predefine method thake ex: findById) "searchUser()" create korbo. mane custom User AIP create korchi. 
	 * custom API create er pore, akhon "userRepository(obj)" dot dilei dekhbo searchUser() ache.
	 * note: custom API er jonno onek somoy UserController class a kono kaj korte hoy na ex: ei method. and User.java class a jodi ei field na define kora thake to define korte hobe.
	 * 
	 * 
	 * throw new Exception() er jonno  "Add throws declaration korte hobe method er sathe. tokhon abar methodName er opor error asbe, tar jonno "Add exception UserService.findUserByeId() --mane InterfaceNme.methodName()" click korlei UserService(I) er findUserByEmail() method er sathe "throws Exception" bosbe.
	 */
	@Override
	public List<User> searchUser(String query) {
		
		//query tai return kore dilam, ei query tai @RequestParam use kore UserController nibe
		return userRepository.searchUser(query);
				
	}

}
