package com.registerApp.demo.util;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmailOtp(String email, String otp) throws MessagingException {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Varify otp");
		simpleMailMessage.setText("Hello..! your otp is : "+otp);
		javaMailSender.send(simpleMailMessage);
		
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//		mimeMessageHelper.setTo(email);
//		mimeMessageHelper.setSubject("Varify otp");
//		mimeMessageHelper.setText("<div><a href="http://localhost:8080/varify-account?email=%s&otp=%s" target="_blank"> click link to varify </a></div>)".formatting(email,otp), true);
//		
//		javaMailSender.send(mimeMessage);
	}

}
