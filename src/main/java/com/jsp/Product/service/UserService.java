package com.jsp.Product.service;

import org.springframework.http.ResponseEntity;

import com.jsp.Product.Entity.User;
import com.jsp.Product.requestdtos.UserRequest;
import com.jsp.Product.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService {

	ResponseEntity<ResponseStructure<User>> saveUser(@Valid UserRequest userRequest);

	ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user);

}
