package com.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.quiz.model.Subject;
import com.quiz.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("subject")
	public String list(Model model) {
		model.addAttribute("listSubject", subjectService.listAll());
		model.addAttribute("subject", new Subject());
		return "html/quanlymon";
	}
	
	@PostMapping("subject/save")
	public String save(@ModelAttribute("subject") Subject subject) {
		subjectService.save(subject);
		return "redirect:/subject";
	}
}
