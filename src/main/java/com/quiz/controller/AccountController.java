package com.quiz.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quiz.model.Account;
import com.quiz.model.Subject;
import com.quiz.service.AccountService;
import com.quiz.service.SubjectService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String listaAll(Model model) {
		return pagination(1, null, model);
	}

	@RequestMapping("account/page/{pageNo}")
	public String pagination(@PathVariable(name = "pageNo") int pageNo, @Param("keyword") String keyword, Model model) {
		int pageSize = 5;
		Page<Account> page = accountService.pagiantion(pageNo, pageSize, keyword);
		List<Account> listAccount = page.getContent();
		List<Subject> listSubject = subjectService.listAll();
		model.addAttribute("listAccount", listAccount);
		model.addAttribute("listSubject", listSubject);
		model.addAttribute("account", new Account());
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "html/quanlytaikhoan";
	}

	@RequestMapping(value = "account/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("account") Account account, HttpSession session) throws ParseException {
		Account current = (Account) session.getAttribute("account");
		if (account.getAccountCode() != null) {
			account.setAvtPath("avatar-default.jpg");
			account.setUpdateDate(new Date());
			account.setUpdateBy(current.getAccountId());
			accountService.save(account);
		} else {
			account.setAccountCode("QA" + (accountService.getMaxId() + 1));
			account.setAvtPath("avatar-default.jpg");
			account.setCreateDate(new Date());
			account.setCreateBy(current.getAccountId());
			accountService.save(account);
		}
		return "redirect:/account";
	}

	@RequestMapping("account/delete/{id}")
	public String delete(@PathVariable(name = "id") int id, Model model) {
		accountService.delete(id);
		return "redirect:/account";
	}

	@RequestMapping("account/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("html/suaquanlytaikhoan");
		Account account = accountService.get(id);
		mav.addObject("account", account);
		return mav;
	}
}
