package com.ste.inventorymanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.model.User;
import com.ste.inventorymanagement.model.UserPasswords;
import com.ste.inventorymanagement.payLoad.UserPayload;
import com.ste.inventorymanagement.repository.RolesRepository;
import com.ste.inventorymanagement.repository.UserPasswordsRepository;
import com.ste.inventorymanagement.repository.UserRepository;
import com.ste.inventorymanagement.util.PasswordUtils;

@Service
public class UserService {
	private Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserPasswordsRepository userPasswordsRepository;
	
	public UserPayload createUser(UserPayload userPayload) {
		User user = new User();
		UserPasswords userPasswords = new UserPasswords();
		user = mapUserPayloadToUser(user, userPayload);
		user.setPasswordLastUpdatedAt(new java.sql.Date(System.currentTimeMillis()));
		user.setInvalidAttemptsCount(0);
		try {
			user = userRepository.save(user);
			userPasswords.setUserId(user);
			userPasswords.setPassword(user.getPassword());
			userPasswords = userPasswordsRepository.save(userPasswords);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Could not create User with duplicate User Id or Email Id");
		}

		mapUserToUserPayload(userPayload, user);
		return userPayload;

	}

	private User mapUserPayloadToUser(User user, UserPayload userPayload) {
		if (null != userPayload.getRoleId() && (0L != userPayload.getRoleId())) {
			user.setRoleId(roleRepository.findById(userPayload.getRoleId()).get());

		}

		else {
			user.setRoleId(null);
		}

		user.setUserid(userPayload.getUserid());
		user.setPhoneNo(userPayload.getPhoneno());
		user.setEmailAddress(userPayload.getEmailAddress());
		user.setName(userPayload.getName());		
		user.setPassword(PasswordUtils.generateencryptPassword(userPayload.getPassword()));
		return user;
	}

	public UserPayload updateUser(UserPayload userPayload) {
		Optional<User> optUser = userRepository.findById(userPayload.getId());
		final String oldPassword = optUser.get().getPassword();
		User user = optUser.get();
		user = mapUserPayloadToUser(user, userPayload);
		if(!oldPassword.equals(user.getPassword())) {
			user.setPasswordLastUpdatedAt(new java.sql.Date(System.currentTimeMillis()));
			user.setInvalidAttemptsCount(user.getInvalidAttemptsCount());
			UserPasswords userPassword = new UserPasswords();
			List<UserPasswords> userPasswordsList = new ArrayList<UserPasswords>();
		    userPasswordsList = userPasswordsRepository.getUserPasswordsByUserId(user.getId());
			if(userPasswordsList.isEmpty()) {
				userPassword.setPassword(user.getPassword());
				userPassword.setUserId(userRepository.findById(user.getId()).get());
				userPasswordsRepository.save(userPassword);
			}
			else {
				List<String> passwordsList = new ArrayList<String>();
				for(UserPasswords userPasswords : userPasswordsList) {
					passwordsList.add(userPasswords.getPassword());
				}
				if(passwordsList.contains(user.getPassword())) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Cannot change to your previous password please use a different password");
				}
				else {
					if(passwordsList.size() != 8) {
						userPassword.setPassword(user.getPassword());
						userPassword.setUserId(user);
						userPasswordsRepository.save(userPassword);
					}
					else {
						//get last updated record and reupdate it with new date and password or delete it and add new userPasswords
						List<UserPasswords> existingUserPasswords = new ArrayList<UserPasswords>();
						existingUserPasswords = userPasswordsRepository.getUserPasswordsByDateOrder();
						userPasswordsRepository.delete(existingUserPasswords.get(0));
						userPassword.setPassword(user.getPassword());
						userPassword.setUserId(user);
						userPasswordsRepository.save(userPassword);
					}
				}
			}
		}
		try {
			user = userRepository.save(user);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Could not create User with duplicate User Id or Email Id");
		}
		UserPayload updatedPayload = new UserPayload();
		updatedPayload = mapUserToUserPayload(updatedPayload, user);


		return updatedPayload;
	}

	private UserPayload mapUserToUserPayload(UserPayload userPayload, User user) {
		userPayload.setPhoneno(user.getPhoneNo());
		userPayload.setId(user.getId());
		userPayload.setName(user.getName());
		userPayload.setEmailAddress(user.getEmailAddress());
		userPayload.setStatus(user.getStatus());
		userPayload.setCreatedAt(user.getCreatedAt());
		userPayload.setCreatedBy(user.getCreatedBy());
		userPayload.setUpdatedAt(user.getUpdatedAt());
		userPayload.setUpdatedBy(user.getUpdatedBy());
		// userPayload.setPassword(user.getPassword());
		userPayload.setUserid(user.getUserid());
		if (null != user.getRoleId()) {
			userPayload.setRoleId(user.getRoleId().getId());
		} else
			userPayload.setRoleId(null);

		return userPayload;
	}

	public UserPayload updateUserActiveStatus(Long id, boolean status) {

		/*
		 * Optional<User> optNotReceivedItems = userRepository.findById(id); if
		 * (!(optNotReceivedItems.isPresent())) { logger.error("User detail with id = "
		 * + id + " not found."); throw new ResponseStatusException(HttpStatus.OK,
		 * "Could not find User detail with IssueId By issueId =" + id); } UserPayload
		 * userPayload = new UserPayload();
		 * 
		 * User user = optNotReceivedItems.get(); user.setStatus(status);
		 * 
		 * user = userRepository.save(user); //userPayload =
		 * mapIssueToIssuePayload(issuePayload, item);
		 * 
		 */

		return null;// issuePayload;

	}

}
