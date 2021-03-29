package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.ExamQuestion;
import com.quiz.repository.ExamQuestionRepository;

@Service
public class ExamQuestionService {
	@Autowired
	private ExamQuestionRepository examQuestionRepository;
	
	public List<ExamQuestion> listAll() {
		return examQuestionRepository.findAll();
	}
	
	public void save(ExamQuestion examQuestion) {
		examQuestionRepository.save(examQuestion);
	}
	
	public ExamQuestion get(Integer id) {
		return examQuestionRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		examQuestionRepository.deleteById(id);
	}
	public void deleteAll(List<ExamQuestion> lisExamQuestions) {
		examQuestionRepository.deleteAll(lisExamQuestions);
	}
	public List<String> get(String examCode) {
		return examQuestionRepository.findQuestionCodeByExamCode(examCode);
	}
	public List<ExamQuestion> getExamQuestionByExamCode(String examCode) {
		return examQuestionRepository.findByExamCode(examCode);
	}
	
	public ExamQuestion findExamQuestionByExamCodeAndQuesionCode(String examCode, String questionCode) {
		return examQuestionRepository.findExamQuestionByExamCodeAndQuesionCode(examCode, questionCode);
	}
}
