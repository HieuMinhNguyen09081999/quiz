package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Question;
import com.quiz.service.QuestionService;

@RestController
public class CallAjaxController {
	
	@Autowired
	private QuestionService questionService;
	

	@GetMapping("/getQuestion/{subjectId}")
	public List<Question> listQuestionBySubjectId(@PathVariable("subjectId") Integer id) {
		return questionService.findQuestionBySubjectId(id);
	}
	
}
