package com.quiz.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.quiz.model.Account;
import com.quiz.service.AccountService;


@Controller
public class PersonalController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping("/personal")
	public String personal(Model model, HttpSession session) {
		model.addAttribute("account", session.getAttribute("account"));
		return "html/thongtincanhan";
	}
	
	@RequestMapping(value = "/personal/save", method = RequestMethod.POST) 
	public String save(@ModelAttribute(name = "account") Account account, MultipartFile file, HttpSession session) {
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		if(fileName.contains("..")) {
//			System.out.println("Invalid");
//		}
//		try {
//			account.setAvtPath(Base64.getEncoder().encodeToString(file.getBytes()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Path path = Paths.get("upload/");
		try {
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, path.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(file.isEmpty()) {
			Account accountSession = (Account) session.getAttribute("account");
			account.setAvtPath(accountSession.getAvtPath());
		} else {
			account.setAvtPath(file.getOriginalFilename().toLowerCase());
		}
		accountService.save(account);
		session.setAttribute("account", account);
		return "redirect:/personal";
	}
}
