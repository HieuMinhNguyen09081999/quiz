package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
	@Query("select t from Test t where t.historyId =:historyId")
	public List<Test> findAll(@Param("historyId")Integer historyId);
}
