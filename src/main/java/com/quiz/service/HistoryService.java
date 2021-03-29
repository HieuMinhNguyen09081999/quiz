package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quiz.model.History;
import com.quiz.repository.HistoryRepository;

@Service
public class HistoryService {
	@Autowired
	private HistoryRepository historyRepository;

	public List<History> listAll() {
		return historyRepository.findAll();
	}
	
	public History save(History history) {
		return historyRepository.save(history);
	}
	
	public History get(Integer id) {
		return historyRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		historyRepository.deleteById(id);
	}
	
	public Page<History> pagination(int pageNo, int pageSize, String keyword) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		if(keyword != null) {
			return historyRepository.findAll(keyword, pageable);
		}
		return historyRepository.findAll(pageable);
	}
}
