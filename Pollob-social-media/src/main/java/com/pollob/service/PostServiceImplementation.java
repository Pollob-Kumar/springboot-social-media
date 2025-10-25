package com.pollob.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional; // Optional class use korar jonno import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // @Service annotation use korar jonno import

import com.pollob.models.Post;
import com.pollob.models.User;
import com.pollob.repository.PostRepository;
import com.pollob.repository.UserRepository;


/*
 * "PostServiceImplementation" class a implements(diff= implement) korlam "UserService" Interface k. jate "UserService(I)" a joto un-implement method ache sob access kora jai and segula akhane implement korte pari.
 * API implement korai holo business logic.
 * un-implement method automatically akhane cole asbe, jokhon "UserServiceImplementation" a error dekhabe tokhon class er opore click kore "Add unimplemented method" click korlei sob automatically cole asbe.
 * Ei class, business class logic tai class er opore "@Service" annotation dite hobe.
 * @Service annotation bole Spring ke, ei class ta ekta Service class hisebe use korte
 */
@Service
public class PostServiceImplementation implements PostService {
	
	/*
	 * PostRepository automatic inject hobe
	 * @Autowired= auto connection
	 * "PostRepository" k import(import korlam karon "JpaRepository" er sokol kichu ache "PostRepository" er majhe) and tar object create korlam "PostRepository".
	 */
	@Autowired
	PostRepository postRepository;
	
	/*
	 * UserService inject hobe, jeta diye user er info pawa jabe
	 * "UserService" k import and tar object create korlam "userRepository".
	 *  @Autowired= auto connection
	 */
	@Autowired
	UserService userService;

	/*
	 * UserRepository inject hobe, user object save/update korar jonno
	 * "UserRepository" k import(import korlam karon "JpaRepository" er sokol kichu ache "UserRepository" er majhe) and tar object create korlam "userRepository".
	 *  @Autowired= auto connection
	 */
	@Autowired
	UserRepository userRepository;
	
	//ei method ta notun post create korar jonno

	/*
	 * "@Override" karon different class but akoi method name and parameters same.
	 * "createNewPost" method er body te ja ache, sob "createNewPost" method er logic
	 * ei method ta k "PostService" interface use kore, "PostController" class-er "createNewPost" method theke control korbo.
	 */
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		// userId diye user ke database theke ber kora hocche
		User user=userService.findUserById(userId);
		
		// ekta notun Post object create kora hocche
		Post newPost=new Post();
		newPost.setCaption(post.getCaption());// caption set kora hocche
		newPost.setImage(post.getImage()); // image set kora hocche
		newPost.setCreateAt(LocalDateTime.now()); // eta comment kora ache, time set kora hoy nai
		newPost.setVideo(post.getVideo()); // video set kora hocche
		newPost.setUser(user); // je user post ta create korche tar info set kora hocche
		
		return postRepository.save(newPost);
	}

	 //ei method ta post delete korar jonno
	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);  //(solved) ekhane postId er jaygay userId use kora hoyeche (eta ekta bug)
		User user=userService.findUserById(userId); // user ke ber kora hocche
		
		// check kora hocche je user nijer post delete korche kina
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("you can't delete anothor users post"); // jodi notun user hoy, error throw kore
		}
		
		postRepository.delete(post); // post delete kora hocche
		return "post deleted successfully"; // success message return hocche
	}

	
	//ei method ta user er sob post khuje ber kore
	@Override
	public List<Post> findPostByUserId(Integer userId) {
		
		return postRepository.findPostByUserId(userId); // repository theke data fetch kore return kore
	}

	// ei method ta specific ekta post khuje ber kore id diye
	@Override
	public Post findPostById(Integer postId) throws Exception {
		
		// post khuja hocche optional object hishebe
		Optional<Post> opt=postRepository.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("Post not found with id"+postId); // post na pele error throw kore
		}
		
		return opt.get(); // post pele seta return kore
	}

	// ei method ta sob post return kore
	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll(); // sob post list akare return kore
	}

	// 6️⃣ ei method ta post "save" or "unsave" korar jonno
	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		
		Post post=findPostById(postId); //(solved) ekhaneo bug ache (postId er jaygay userId use kora hoyeche)
		User user=userService.findUserById(userId);	 // user ke ber kora hocche	
		
		// check kora hocche user ager theke post ta save koreche kina
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post); // jodi already save thake tahole remove kore
		}
		else user.getSavedPost().add(post); // otherwise post ta save list e add kore
		userRepository.save(user);  // updated user object database e save kore
		
		return post; // post object return kore
	}

	//ei method ta post like/unlike korar jonno
	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId); //(solved) ekhaneo same bug ache (postId er jaygay userId use kora hoyeche)
		User user=userService.findUserById(userId);  // user ke ber kora hocche
		
		// check kora hocche user already like koreche kina
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user); // jodi already like thake tahole unlike kore
		}
		else {
			post.getLiked().add(user); // otherwise like add kore
		}
		
		return postRepository.save(post); // post update kore save kore return kore
	}

}
