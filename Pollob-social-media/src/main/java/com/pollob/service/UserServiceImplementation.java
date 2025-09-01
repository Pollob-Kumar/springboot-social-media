package com.pollob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pollob.models.User;
import com.pollob.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	/*
	 * "UserRepository" k import(import korlam karon "JpaRepository" er sokol kichu ache "UserRepository" er majhe) and tar object create korlam "userRepository".
	 *  @Autowired= auto connection
	 */
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		
		
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

	@Override
	public User findUserByEmail(String email) {
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		
		User user1=findUserById(userId1);
		
		User user2=findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		
		user1.getFollowings().add(user2.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		return user1;
	}

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

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
				
	}

}
