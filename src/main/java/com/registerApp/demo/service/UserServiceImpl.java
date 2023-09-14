package com.registerApp.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registerApp.demo.dto.LoginDto;
import com.registerApp.demo.dto.RegisterDto;
import com.registerApp.demo.entity.User;
import com.registerApp.demo.repository.UserRepository;
import com.registerApp.demo.util.EmailUtil;
import com.registerApp.demo.util.OtpUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private OtpUtil otpUtil;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String register(RegisterDto registerDto) {
		log.info("In register user service implementation");
		String otp = otpUtil.genetateOtp();
		try {
			emailUtil.sendEmailOtp(registerDto.getEmail(), otp);
		} catch (MessagingException e) {
			throw new RuntimeErrorException(null, "Unable to send email try again");
		}
		User user = new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setPassword(registerDto.getPassword());
		user.setOtp(otp);
		user.setOtpGenLocalDateTime(LocalDateTime.now());
		userRepository.save(user);
		return "User registration successful";
	}

	@Override
	public String varifyAccount(String email, String otp) {
		log.info("In user implementation User varification");
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email :" + email));
		if (user.getOtp().equals(otp)
				&& Duration.between(user.getOtpGenLocalDateTime(), LocalDateTime.now()).getSeconds() < (5 * 60)) {
			user.setActive(true);
			userRepository.save(user);
			return "Otp varifies.. You can login";
		}
		
		return "Please regenerate otp and try again";
	}

	@Override
	public String regenerateOtp(String email) {
		log.info("In service implementation regeneration of otp");
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found for this email :" + email));
		String otp = otpUtil.genetateOtp();
		try {
			emailUtil.sendEmailOtp(email, otp);
		} catch (MessagingException e) {
			throw new RuntimeErrorException(null, "Unable to send email try again");
		}
		user.setOtp(otp);
		user.setOtpGenLocalDateTime(LocalDateTime.now());
		return "Email sent.. varify your account within 1 minute";
	}

	@Override
	public String login(LoginDto loginDto) {
		log.info("In service implementation user ligin");
		User user = userRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found for this email : " + loginDto.getEmail()));
		if(!loginDto.getPassword().equals(user.getPassword())) {
			return "Password is incorrect";
		}else if(!user.isActive()) {
			return "User is not in active state";
		}
		return "Login successful";
	}

}
