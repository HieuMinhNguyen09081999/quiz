package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quiz.model.Question;
import com.quiz.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> listAll() {
		return questionRepository.findAll();
	}

	public void save(Question question) {
		questionRepository.save(question);
	}

	public Question get(Integer id) {
		return questionRepository.findById(id).get();
	}

	public Question get(String questionCode) {
		return questionRepository.findQuestionByQuestionCoe(questionCode);
	}

	public void delete(Integer id) {
		questionRepository.deleteById(id);
	}

	public Integer getMaxId() {
		return questionRepository.getMaxId();
	}

	public Page<Question> pagination(int pageNo, int pageSize, String keyword) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		if (keyword != null) {
			return questionRepository.findAll(keyword, pageable);
		}
		return questionRepository.findAll(pageable);
	}

	public List<Question> findQuestionEasy(Integer subjectId) {
		return questionRepository.findQuestionEasy(subjectId);
	}

	public List<Question> findQuestionNormal(Integer subjectId) {
		return questionRepository.findQuestionNormal(subjectId);
	}

	public List<Question> findQuestionHard(Integer subjectId) {
		return questionRepository.findQuestionHard(subjectId);
	}
	
	public List<Question> findQuestionBySubjectId(Integer id) {
		return questionRepository.findQuestionBySubjectId(id);
	}
}
