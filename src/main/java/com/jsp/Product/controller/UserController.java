package com.jsp.Product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Product.Entity.User;
import com.jsp.Product.requestdtos.UserRequest;
import com.jsp.Product.service.UserService;
import com.jsp.Product.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody @Valid UserRequest userRequest){
		return service.saveUser(userRequest);
	}
	
	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> updateUser (int userId,@RequestBody User user) {
		return service.updateUser(userId,user);
	}
	
}
