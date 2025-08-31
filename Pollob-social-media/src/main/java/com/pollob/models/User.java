package com.pollob.models;

/*
 * every model class er Controller create na korle, sei class run hobe na. kintu models chara controller run hoy. ex: HomeController
 * Ei(com.rafi.models) package er majhe sokol feature class(ex. share, like, Post e.t.c) thakbe. And Controller package a sei class k control kora hobe.
 */

public class User {

	//id diye user find korar jonno id filed nilam. 2nd
	
	private Integer id;
	//Property set koram (Private) 
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	//default constructor
	public User() {
		// TODO Auto-generated constructor stub
	}


	//Propertir jonno automatic Constructor field use kora and setter getter method use kora.
	public User(Integer id, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	
}