package com.quiz.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quiz.model.Account;
import com.quiz.service.AccountService;

@Controller
public class RegisterController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/register") 
	public String registerView(Model model) {
		model.addAttribute("account", new Account());
		return "html/register";
	}
	
	@RequestMapping(value = "/registerAccount", method = RequestMethod.POST) 
	public String registerAccount(@ModelAttribute(name = "account") Account account) {
		account.setAccountCode("QA" + (accountService.getMaxId() + 1));
		account.setAvtPath("avatar-default.jpg");
		account = accountService.save(account);
		account.setCreateBy(account.getAccountId());
		account.setCreateDate(new Date());
		account.setUpdateBy(account.getAccountId());
		account.setUpdateDate(new Date());
		account = accountService.save(account);
		return "redirect:/";
	}
}
