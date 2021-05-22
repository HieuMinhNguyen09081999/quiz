package com.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("subject/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id) {
		subjectService.delete(id);
		return "redirect:/subject";
	}

	@RequestMapping("subject/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Integer id) {
		Subject subject = subjectService.get(id);
		ModelAndView mav = new ModelAndView("html/suaquanlymon");
		mav.addObject("subject", subject);
		return mav;
	}
}
