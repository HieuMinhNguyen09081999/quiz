package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quiz.model.Exam;
import com.quiz.repository.ExamRepository;

@Service
public class ExamService {
	@Autowired
	private ExamRepository examRepository;

	public List<Exam> listAll() {
		return examRepository.findAll();
	}

	public Exam save(Exam exam) {
		return examRepository.save(exam);
	}

	public Exam get(Integer id) {
		return examRepository.findById(id).get();
	}

	public void delete(Integer id) {
		examRepository.deleteById(id);
	}

	public Page<Exam> pagination(int pageNo, int pageSize, String keyword) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		if(keyword != null) {
			return examRepository.findAll(pageable, keyword);
		}
		return examRepository.findAll(pageable);
	}

	public Page<Exam> paginationTest(int pageNo, int pageSize, Integer subjectId) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		if(subjectId != null) {
			return examRepository.findAllTestBySubjectId(pageable, subjectId);
		}
		return examRepository.findAllTest(pageable);
	}

	public Page<Exam> paginationReal(int pageNo, int pageSize, Integer subjectId) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		if(subjectId != null) {
			return examRepository.findAllRealBySubjectId(pageable, subjectId);
		}
		return examRepository.findAllReal(pageable);
	}

	public Integer findMaxExamId() {
		return examRepository.findMaxExamId();
	}

	public Exam findExamByCode(String ex) {
		return examRepository.findExamByCode(ex);
	}
}
