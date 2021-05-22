package com.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotPassword {
	@GetMapping("fortgotpassword")
	public String forgotPassword() {
		return "html/quenmk";
	}
}
