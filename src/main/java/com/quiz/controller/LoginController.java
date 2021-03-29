package com.quiz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quiz.model.Account;
import com.quiz.model.Login;
import com.quiz.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "html/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute(name = "login") Login login, HttpServletRequest request, Model model) {
		Account account = loginService.login(login.getEmail(), login.getPassword());
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		if(account != null && account.getRole() == 0) {
			return "redirect:/account";
		} else if (account != null && account.getRole() == 1){
			return "redirect:/exam";
		} else if (account != null && account.getRole() == 2){
			return "redirect:/test";
		} else {
			model.addAttribute("message", "Đăng nhập thất bại!");
			return "html/login";
		}
	}
}
