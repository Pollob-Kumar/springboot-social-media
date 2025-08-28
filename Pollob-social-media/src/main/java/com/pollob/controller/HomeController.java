package com.pollob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//note: controller and API define kora chara project run hobe na
/*
 *first API (@RestController) define kora Controller class er opore. 
 *@RestController API spring-boot k bole dey, ai(HomeController) class a Controller releted Rest endpoint/API define korbo.
*/
@RestController
public class HomeController {

	//database theke data read korar jonno @GetMapping and "/home" holo endpoint.
	@GetMapping
	public String homeController(){
		return "this is home controller";
	}
	
	//"/home" holo endpoint.
	@GetMapping("/home")
	public String homeController2(){
		return "this is home controller 2";
	}
}