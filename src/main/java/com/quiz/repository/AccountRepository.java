package com.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("SELECT MAX(accountId) FROM Account")
	public Integer findMaxAccountId();
	
	@Query("SELECT a FROM Account a WHERE a.fullName LIKE %:keyword%")
	public Page<Account> findAll(@Param("keyword") String keyword, Pageable pageable);
	
	public Account findAccoutByEmailAndPassword(String email, String password);
}
