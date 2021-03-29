package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.model.ExamQuestion;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Integer> {
	@Query("select questionCode from ExamQuestion e where e.examCode = :examCode")
	public List<String> findQuestionCodeByExamCode(@Param("examCode") String examCode);
	
	@Query("select e from ExamQuestion e where e.examCode = :examCode")
	public List<ExamQuestion> findByExamCode(@Param("examCode") String examCode);
	
	@Query("select q from ExamQuestion q where q.examCode =:examCode and q.questionCode =:questionCode") 
	public ExamQuestion findExamQuestionByExamCodeAndQuesionCode(@Param("examCode") String examCode, @Param("questionCode") String questionCode);
}
