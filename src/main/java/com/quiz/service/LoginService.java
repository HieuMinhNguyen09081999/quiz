package com.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Account;
import com.quiz.repository.AccountRepository;

@Service
public class LoginService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account login(String account, String password) {
		return accountRepository.findAccoutByEmailAndPassword(account, password);
	}
}
