package com.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Account;
import com.quiz.repository.AccountRepository;
import com.quiz.util.EmailUtil;

@Service
public class ForgotPasswordService {
	
	@Autowired
	private AccountRepository accountRepository;

	public Account checkCountdown(String code, String password, String email) {
		Account account = accountRepository.findAccoutByEmail(email);
		if(EmailUtil.getToken().equals(code)) {
			account.setPassword(password);
			return accountRepository.save(account);
		}
		else {
			return null;
		}
	}
	
}
