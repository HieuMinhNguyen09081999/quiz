package com.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
	@Query("SELECT a FROM History a WHERE a.examCode LIKE %:keyword%")
	public Page<History> findAll(@Param("keyword") String keyword, Pageable pageable);
}
