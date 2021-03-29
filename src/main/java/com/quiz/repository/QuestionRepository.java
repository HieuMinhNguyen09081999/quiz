package com.quiz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	@Query("select max(questionId) from Question")
	public Integer getMaxId();
	
	@Query("SELECT q FROM Question q WHERE q.question LIKE %:keyword%")
	public Page<Question> findAll(@Param("keyword")String keyword, Pageable pageable);
	
	@Query("select q from Question q where q.questionCode =:questionCode")
	public Question findQuestionByQuestionCoe(@Param("questionCode") String questionCode);
	
	@Query("select q from Question q where q.level = 0 and q.subjectId =:subjectId")
	public List<Question> findQuestionEasy(@Param("subjectId") Integer subject);
	
	@Query("select q from Question q where q.level = 1 and q.subjectId =:subjectId")
	public List<Question> findQuestionNormal(@Param("subjectId") Integer subject);
	
	@Query("select q from Question q where q.level = 2 and q.subjectId =:subjectId")
	public List<Question> findQuestionHard(@Param("subjectId") Integer subject);
	
	public List<Question> findQuestionBySubjectId(Integer subjectId);
}
