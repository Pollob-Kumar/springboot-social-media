package com.pollob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pollob.config.JwtProvider;
import com.pollob.models.User;
import com.pollob.repository.UserRepository;
import com.pollob.request.LoginRequest;
import com.pollob.response.AuthResponse;
import com.pollob.service.CustomerUserDetailsService;
import com.pollob.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetails;
	
	/*
	 * ei method er vitore "UserService" interface er "registerUser()" method use korbo, ja "UserServiceImplementation" class a implement korchi. 
	 */
	
	/*database jonno 4-V te opore niye aslam, jekono jaigate rakha jai ata
	 * @PostMapping= jokhon database a data add kora lagbe tokhon "@PostMapping" use hoy.
	 * @RequestBody= jokhon kono frontend libray(ex. POSTMAN) theke data sent kori, tokhon data body-te sent korbo, ja database-a add hobe.
	 * akhane(@RequestBody User user) "User" holo datatype and "user" holo obj/variable.
	 * data insert er jonno ei "@PostMapping"
	 */
	
	//     /auth/signup
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception{
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if(isExist!=null) {
			throw new Exception("this email already used with another account");
		}

		//User class er object create korlam
		User newUser=new User();
		
		/*
		 * user.getEmail()= "user" obj theke(ja "@RequestBody" method er parameter-a  "User" class er obj kore nichilam.) email ber kore ane( getter method diye, ja User.java class a define kora ache.) 
		 * newUser.setEmail(...)= ber kora email "newUser" obj-er Email a bosiye dey(setter method diye, ja User.java class a define kora ache)
		 */
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));

		//database a data save korar jonno "save" method ja ache "userRepository" er majhe.
		User savedUser=userRepository.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token, "Register Success");
		
		//karon "savedUser" obj a save data ache.
		return res;
		
	}
	
	//real endpoin hobe auth/singin
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token, "Login Success");
		
		//karon "savedUser" obj a save data ache.
		return res;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not matched/wrong password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
