package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Test;
import com.quiz.repository.TestRepository;

@Service
public class TestServie {
	@Autowired
	private TestRepository testRepository;

	public Test save(Test test) {
		return testRepository.save(test);
	}

	public List<Test> listTest(Integer historyId) {
		return testRepository.findAll(historyId);
	}
}
