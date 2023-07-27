package com.example.app1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app1.dto.ChangePasswordStatus;
import com.example.app1.dto.EmailVerificationStatus;
import com.example.app1.dto.LoginSuccessStatus;
import com.example.app1.dto.ResetPasswordStatus;
import com.example.app1.dto.SendMailOnForgottenPasswordStatus;
import com.example.app1.dto.SignupSuccessStatus;
import com.example.app1.entity.AppUser;
import com.example.app1.entity.PasswordChange;
import com.example.app1.service.AppUserService;

import jakarta.persistence.Id;

@RestController
@RequestMapping("admin")
@CrossOrigin			//to allow any type of client
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;	//interacting with service layer
	
	@PostMapping("signup")
	public ResponseEntity<SignupSuccessStatus> save(@RequestBody AppUser appUser)throws Exception
	
	{
		return ResponseEntity.ok(appUserService.save(appUser));
	}
	/*
	 {
	 	"firstName": "Anil",
	 	"lastName": "Kumar",
		"email" : "A@gmail.com",
		"password": "abc"
	 }
	 http://localhost:9090/admin/signup
	 */
	
		
	@PostMapping("login")
	public ResponseEntity<LoginSuccessStatus> login(@RequestBody AppUser appUser)
	{
		return ResponseEntity.ok(appUserService.login(appUser));
	}
	
	/*
	
	 { 	
		"email" : "A@gmail.com",
		"password": "abc"
	 }
	 */
		
	@PostMapping("sendMailOnForgottenPassword")
	public ResponseEntity<SendMailOnForgottenPasswordStatus>
		sendMailOnForgottenPassword(@RequestBody AppUser appUser)throws Exception
	{
		return ResponseEntity.ok(
				appUserService.sendMailOnForgottenPassword(appUser));
	}
	
	@PostMapping("resetPassword")
	public ResponseEntity<ResetPasswordStatus>
	resetPassword(@RequestParam String email,
					@RequestParam String password,
					@RequestParam String confirmPassword)
	{
		return ResponseEntity.ok(
				appUserService.resetPassword(email, password, confirmPassword));
	}
	
	
	@PostMapping("changePassword")
	public ResponseEntity<ChangePasswordStatus>
	changePassword(@RequestBody PasswordChange passwordChange)
	{
		return ResponseEntity.ok(
				appUserService.changePassword(passwordChange.getEmail(),
						passwordChange.getOldPassword(),
						passwordChange.getPassword(),
						passwordChange.getConfirmPassword()));
	}
	
	@GetMapping("verifyMailId/{token}/{email}")
	public ResponseEntity<EmailVerificationStatus> verifyMailId(@PathVariable String token,@PathVariable String email)
	{
		return ResponseEntity.ok(appUserService.verifyMailId(token, email));
	}
	
}
