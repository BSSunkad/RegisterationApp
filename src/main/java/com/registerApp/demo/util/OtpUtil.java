package com.registerApp.demo.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {

	public String genetateOtp() {
		Random random = new Random();
		int randomNumber = random.nextInt(999999);
		String output = Integer.toString(randomNumber);
		
		// if 1234 output will be 001234
		while (output.length() < 6) {
			output = "0" + output;
		}
		return output;
	}
}
