package com.quiz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Account;
import com.quiz.repository.AccountRepository;
import com.quiz.util.EmailUtil;

@Service
public class ForgotPasswordService {
	
	@Autowired
	private AccountRepository accountRepository;

	public Account checkCountdown(String code, String password, String email, long startTimeSendEmail) {
		Account account = accountRepository.findAccoutByEmail(email);
		if(EmailUtil.getToken().equals(code) && (new Date().getTime() <= startTimeSendEmail + 60000)) {
			account.setPassword(password);
			return accountRepository.save(account);
		}
		else {
			return null;
		}
	}
	
}
