package com.pollob.models;

import java.time.LocalDateTime; // post create howar somoy time track korar jonno
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;  // auto id generate korar jonno
import jakarta.persistence.GenerationType;  // generation technique set korar jonno
import jakarta.persistence.Id;  // primary key define korar jonno

 //@Entity boleche je ei class ta ekta database table hobe
@Entity
public class Post {
	
	 // ei field ta holo primary key
	@Id
	 // id automatic vabe generate hobe
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Integer id;
	private String caption;
	private String image;
	private String video;
	private User user; // (User class theke asbe) je user post ta create koreche, tar info store korbe 
	private List<User> liked=new ArrayList<>(); // ei list e thakbe jara ei post ta like koreche
	private LocalDateTime createAt; // post ta kobe create hoyeche, tar date & time
	
	
	// default constructor (JPA er jonno dorkar hoy)
	public Post() {
		// TODO Auto-generated constructor stub
	}

     
	/* 
	 * eta parameterized constructor.
	 * ei constructor diye sob field(id, caption e.t.c) er value set kora jabe
	 */
	public Post(Integer id, String caption, String image, String video, User user, List<User> liked,
			LocalDateTime createAt) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.user = user;
		this.liked = liked;
		this.createAt = createAt;
	}


	/*
	 * niche getter & setter methods ache
	 * ei methods gula diye private-field (id, caption e.t.c) er value get & set kora jay
	 */

	public Integer getId() {
		return id;        // post er id return kore
	}


	public void setId(Integer id) {
		this.id = id;     // post er id set kore
	}


	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getImage() {
		return image;       // image path return kore
	}


	public void setImage(String image) {
		this.image = image;  // image path set kore

	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public User getUser() {
		return user;       // post creator er user object return kore
	}


	public void setUser(User user) {
		this.user = user;  // user set kore
	}


	public List<User> getLiked() {
		return liked;   // jara like koreche tader list return kore

	}


	public void setLiked(List<User> liked) {
		this.liked = liked;   // liked list set kore
	}


	public LocalDateTime getCreateAt() {
		return createAt;      // post create howar time return kore
	}


	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt; // createAt time set kore
	}
}