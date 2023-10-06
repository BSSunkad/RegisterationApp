package com.registerApp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registerApp.demo.dto.LoginDto;
import com.registerApp.demo.dto.RegisterDto;
import com.registerApp.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		log.info("In register-app Register started");
		log.info("request of registerDto : " + registerDto);
		String register = userService.register(registerDto);
		log.info("response from register service : " + register);
		
		log.info("response from register service : " + register);
		return new ResponseEntity<String>(register, HttpStatus.OK);

		// return new ResponseEntity<>(userService.register(registerDto),HttpStatus.OK);
	}

	@PutMapping("/varify-account")
	public ResponseEntity<String> varifyAccount(@RequestParam String email, @RequestParam String otp) {
		log.info("In register-app varification controller started");
		log.info("responce from varify account"+email+" "+otp);
		String varifyAccount = userService.varifyAccount(email, otp);
		return new ResponseEntity<String>(varifyAccount, HttpStatus.OK);
	}

	@PutMapping("/regenerate-otp")
	public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
		log.info("In register-app otp re-generation controller started");
		log.info("responce from regenerate otp for mail "+email);
		String regenerate = userService.regenerateOtp(email);
		return new ResponseEntity<String>(regenerate, HttpStatus.OK);
	}

	@PutMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		log.info("In register-app login controller started");
		log.info("responce from login is "+loginDto);
		String login = userService.login(loginDto);
		return new ResponseEntity<String>(login, HttpStatus.OK);
	}
}
