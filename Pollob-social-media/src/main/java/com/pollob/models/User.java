package com.pollob.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * every model class er Controller create na korle, sei class run hobe na. kintu models chara controller run hoy. ex: HomeController
 * Ei(com.rafi.models) package er majhe sokol feature class(ex. share, like, Post e.t.c) thakbe. And Controller package a sei class k control kora hobe.
 */

/*
 * Spring boot er maddhome automatically Table create hobe.
 * @Entity= ata dara spring boot k bolchi "User" class er "User Table" create koro and User class er property gulo columns hisabe rakho.  
 */


@Entity

/*
 * Custom table name. Mane Class namer table create hobe na.
 * note: jodi ekta class er 1st table create thake data soho, and 2nd time oi class er table create korle(jemon default class or custom vabe) 1st table er sathe replace hobe na.
 * 
 */
@Table(name="users") 
public class User {

	/*
	 * id diye user find korar jonno id filed nilam.
	 * @Id= Spring boot k bolchi, je propertir opor "@Id" ache take Unique value/Identifire hisabe rakho.
	 */
	@Id
	private Integer id;
	
	/*
	 * Custom column name.
	 * ager column a jodi data thake and pore custom/default vabe abar column name dey, tahole ager column er sathe new column replace hobe na. database a sob column er sese new ekta column create hobe. and ager column a "null" value store hobe.
	 */
	@Column(name="my_name")
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