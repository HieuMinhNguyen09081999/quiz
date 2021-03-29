package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.quiz.model.History;
import com.quiz.model.Question;
import com.quiz.model.Test;
import com.quiz.service.HistoryService;
import com.quiz.service.QuestionService;
import com.quiz.service.TestServie;

@Controller
public class HistoryController {

	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private TestServie testService;
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("history")
	public String listAll(Model model) {
		return pagination(1, null, model);
	}
	
	@RequestMapping("history/page/{pageNo}")
	public String pagination(@PathVariable(name = "pageNo") int pageNo, @Param("keyword") String keyword, Model model) {
		int pageSize = 5;
		Page<History> page = historyService.pagination(pageNo, pageSize, keyword);
		List<History> listHistory = page.getContent();
		model.addAttribute("listHistory", listHistory);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "html/xemlichsu";
	}
	
	@RequestMapping("history/detail/{id}") 
	public ModelAndView detail(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView("html/chitietlichsu");
		List<Test> listTest = testService.listTest(id);
		List<Question> listQuestion = questionService.listAll();
		mav.addObject("listTest", listTest);
		mav.addObject("listQuestion", listQuestion);
		return mav;
	}
}
