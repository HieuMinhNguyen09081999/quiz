package com.quiz.controller;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Account;
import com.quiz.model.Question;
import com.quiz.service.ForgotPasswordService;
import com.quiz.service.QuestionService;
import com.quiz.util.EmailUtil;

@RestController
public class CallAjaxController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	private long startTime;

	@GetMapping("/getQuestion/{subjectId}")
	public List<Question> listQuestionBySubjectId(@PathVariable("subjectId") Integer id) {
		return questionService.findQuestionBySubjectId(id);
	}
	

	@PostMapping("/changepassword/{email}")
	public ResponseEntity<Object> changepassword(@PathVariable String email) throws MessagingException {
		EmailUtil.sendEmail(email, 1);
		startTime = new Date().getTime();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping("/fortgotpassword")
	public ResponseEntity<Object> forgotPassword(@RequestParam(name = "email") String email, 
								@RequestParam(name = "password") String password,
								@RequestParam(name = "code") String code) {
		Account acount = forgotPasswordService.checkCountdown(code, password, email, startTime);
		if(acount != null) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
