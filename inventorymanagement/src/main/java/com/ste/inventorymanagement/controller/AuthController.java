package com.ste.inventorymanagement.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.exceptions.AuthFailException;
import com.ste.inventorymanagement.model.Login;
import com.ste.inventorymanagement.model.User;
import com.ste.inventorymanagement.repository.RolesRightsRepository;
import com.ste.inventorymanagement.repository.UserRepository;
import com.ste.inventorymanagement.util.PasswordUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RolesRightsRepository rolesRightsRepository;
	
	@PostMapping("/login")
	public Map<String, Object> validateUser(@Valid @RequestBody Login user) {
		String userName = user.getUserName();
		Map<String, Object> map = new HashMap<String, Object>();
//
//		Login lm_temp = loginRepository.findByUserName(userName)
//				.orElseThrow(() -> new AuthFailException("User", "UserName", userName));

		User tmp_user = userRepository.findByUserIddetails(userName)
				.orElseThrow(() -> new AuthFailException("User", "UserName", userName));
		String providedPassword = user.getPassword();

		String securePassword = tmp_user.getPassword();

		boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, securePassword);
		// boolean passwordMatch=true;
		if (!passwordMatch) {
			Integer count = tmp_user.getInvalidAttemptsCount();
			int attemptsLeft = 5 - count;
			if(attemptsLeft != 0) {
			tmp_user.setInvalidAttemptsCount(count+1);			
			userRepository.save(tmp_user);
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You have "+attemptsLeft+" attempts left to login");
			}
			else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You have 0 attempts left to login, Please contact administrator");
			}
			//throw new AuthFailException("User", "UserName", userName);
		}
		if(tmp_user.getInvalidAttemptsCount() == 5) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You have 0 attempts left to login, Please contact administrator");
		}
		int temp_Userid = tmp_user.getId().intValue();
		tmp_user.setInvalidAttemptsCount(0);			
		userRepository.save(tmp_user);
		Date currentDate = new Date(System.currentTimeMillis());
		Date passwordLastUpdatedDate = tmp_user.getPasswordLastUpdatedAt();
		long currentDateDays =currentDate.getTime()/(1000*60*60*24);
		long passwordLastUpdatedDateDays = passwordLastUpdatedDate.getTime()/(1000*60*60*24);
		if(currentDateDays-passwordLastUpdatedDateDays>90) {
			System.out.println("++++++++++++++++++++++ change Password");
		}
		map.put("user", tmp_user);
		map.put("rights", rolesRightsRepository.getAllRightsForTheRole(tmp_user.getRoleId().getId()));
		return map;

	}

}
