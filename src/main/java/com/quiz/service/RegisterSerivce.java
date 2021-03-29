package com.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Account;
import com.quiz.repository.AccountRepository;

@Service
public class RegisterSerivce {
	@Autowired
	private AccountRepository accountRepository;
	
	public Account register(Account account) {
		return accountRepository.save(account);
	}
}
