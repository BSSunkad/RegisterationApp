package com.registerApp.demo.service;

import com.registerApp.demo.dto.LoginDto;
import com.registerApp.demo.dto.RegisterDto;

public interface UserService {
	
	public String register(RegisterDto registerDto);

	public String varifyAccount(String email, String otp);

	public String regenerateOtp(String email);

	public String login(LoginDto loginDto);

}
