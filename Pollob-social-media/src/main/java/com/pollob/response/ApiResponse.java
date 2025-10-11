package com.pollob.response;

/*
 * ApiResponse class ta mainly API response ke properly return korar jonno use hoy.
 * ekhane ekta message (text) ar ekta status (true/false) thake.
 */
public class ApiResponse {
	
	private String message; // API theke asha message
	private boolean status; // operation success hole true, fail hole false
	
	// 🔹 Default constructor
	public ApiResponse() {
		// TODO Auto-generated constructor stub   // jodi kono default object create korte hoy
	}
	
	
	// 🔹 Parameterized constructor
	public ApiResponse(String message, boolean status) {
		super();
		this.message = message; // message set kora
		this.status = status;  // status set kora
	}


	// 🔹 Getter & Setter methods

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

	
}
