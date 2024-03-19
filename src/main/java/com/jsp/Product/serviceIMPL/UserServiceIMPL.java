package com.jsp.Product.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Product.Entity.User;
import com.jsp.Product.Exception.UserNotFoundById;
import com.jsp.Product.repository.UserRepository;
import com.jsp.Product.requestdtos.UserRequest;
import com.jsp.Product.service.UserService;
import com.jsp.Product.utility.ResponseStructure;

@Service
public class UserServiceIMPL implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ResponseStructure<User> structure;


	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(UserRequest userRequest) {

		User newUsers = repository.save(mapToUser(userRequest));

		return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
				.setMessage("User saved Successfully")
				.setData(newUsers));
	}

	public User mapToUser(UserRequest request) {

		return User.builder().email(request.getEmail())
		.password(request.getPassword())
		.contact(request.getContact())
		.userName(request.getEmail().split("@")[0])
		.build();

	}

	@Override
	public ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user) {

		return repository.findById(userId)
				.map(exitingUser -> {
					user.setUserId(exitingUser.getUserId());
					exitingUser=repository.save(user);
					return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value()).setMessage("User update Successfully!!")
							.setData(user));
				}).orElseThrow(()-> new UserNotFoundById("User not found by Id"));
	}

}
