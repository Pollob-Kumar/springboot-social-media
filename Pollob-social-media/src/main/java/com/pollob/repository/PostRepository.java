package com.pollob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // custom query likhar jonno @Query annotation import kora hoyeche

import com.pollob.models.Post;

/*
 * ei interface(PostRepository) ta database er Post table er upor kaj korar jonno banano hoyeche
 * mane ei Repository(PostRepository) diye amra Post table theke data create, read, update, delete (CRUD) korte parbo
 * PostRepository holo interface ja JpaRepository ke extend korche. karon akhane ready-made CRUD operations (save, findAll, findById, deleteById e.t.c) automatic create kore dibe.
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	/*
	 * @Query annotation diye amra ekta custom JPQL query likhte pari
	 * ekhane query ta holo: select p from Post p where p.user.id = :userId
	 * query tar mane holo je user er id diye query ta run hobe, tar sob post select korbe
	 */
	@Query("select p from Post p where p.user.id=:userId")
	List<Post> findPostByUserId(Integer userId);
}
