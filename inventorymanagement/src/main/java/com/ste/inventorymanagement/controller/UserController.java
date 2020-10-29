package com.ste.inventorymanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.exceptions.ResourceNotFoundException;
import com.ste.inventorymanagement.model.User;
import com.ste.inventorymanagement.payLoad.UserPayload;
import com.ste.inventorymanagement.repository.UserRepository;
import com.ste.inventorymanagement.services.UserService;
import com.ste.inventorymanagement.util.PasswordUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/User")
@ResponseBody
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepository.findAll();

	}

	@GetMapping("/{Id}")
	public User getAllUsersById(@PathVariable(value = "Id") Long Id) {
		// return userRepository.findAll();
		User usernames = userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", Id));
		usernames.setPassword(PasswordUtils.generatedecryotPassword(usernames.getPassword()));
		return usernames;

	}


	@PostMapping("/")
	public UserPayload createUSer(@Valid @RequestBody UserPayload userPayload) {

		UserPayload savedUser = userService.createUser(userPayload);
		return (savedUser);
	}

	@PutMapping("/")
	public ResponseEntity<UserPayload> updateUser(@Valid @RequestBody UserPayload userPayloadList) {
		if (null == userPayloadList) {
			// logger.error("No UserPayloadList sent to the controller to update");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Please pass valid UserPayloadList data to update.");
		}

		UserPayload updatedPayload = userService.updateUser(userPayloadList);
		// updatedUserPayloadList.add(updatedPayload);

		// updatedpacingItemsPayloadList =
		// pacingItemsService.updatePacingItems(pacingItemsPayloadList);
		return ResponseEntity.ok(updatedPayload);
	}

}
