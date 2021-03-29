package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Subject;
import com.quiz.repository.SubjectRepository;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Subject> listAll(){
		return subjectRepository.findAll();
	}
	
	public void save(Subject subject) {
		subjectRepository.save(subject);
	}
	
	public Subject get(Integer id) {
		return subjectRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		subjectRepository.deleteById(id);
	}
}
