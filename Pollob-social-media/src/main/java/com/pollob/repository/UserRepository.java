package com.pollob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pollob.models.User;


/*
 * "JpaRepository(I)" k extends korchi karon tar sokol kichu je "UserRepository(I)" theke access kora jai.
 * <User, id>= "User" dara bole dicchi kon class er jonno ei repository.
 * <T, Integer>= "Integer" dara bole dicchi, "User" class er Unique Value/Identifier er Type holo "Integer"
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	@Query("select u from User u where u.firstName LIKE %:qeury% OR u.lastName LIKE %:query% OR u.email LIKE %:qeury%")
	public List<User> searchUser(@Param("query") String query);

}
