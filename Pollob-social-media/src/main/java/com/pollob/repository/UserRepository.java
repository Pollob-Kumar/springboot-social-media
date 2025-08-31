package com.pollob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pollob.models.User;

/*
 * "JpaRepository(I)" k extends korchi karon tar sokol kichu je "UserRepository(I)" theke access kora jai.
 * <User, id>= "User" dara bole dicchi kon class er jonno ei repository.
 * <T, Integer>= "Integer" dara bole dicchi, "User" class er Unique Value/Identifier er Type holo "Integer"
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
