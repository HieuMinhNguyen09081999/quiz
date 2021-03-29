package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quiz.model.Account;
import com.quiz.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> listAll() {
		return accountRepository.findAll();
	}
	
	public Account save(Account account) {
		return accountRepository.save(account);
	}
	
	public Account get(Integer id) {
		return accountRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		accountRepository.deleteById(id);
	}
	
	public int getMaxId() {
		return accountRepository.findMaxAccountId();
	}
	
	public Page<Account> pagiantion(int pageNo, int pageSize, String keyword) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		if(keyword != null) {
			return accountRepository.findAll(keyword, pageable);
		}
		return accountRepository.findAll(pageable);
	}
}
