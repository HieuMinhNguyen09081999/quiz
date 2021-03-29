package com.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
	@Query("select max(examId) from Exam")
	public Integer findMaxExamId();
	
	@Query("select e from Exam e where e.examCode =:ex")
	public Exam findExamByCode(@Param("ex") String ex);
	
	@Query("select e from Exam e where e.examCode like %:keyword%")
	public Page<Exam> findAll(Pageable pageable, @Param("keyword") String keyword);
	
	@Query("select e from Exam e where e.startTime = null")
	public Page<Exam> findAllTest(Pageable pageable);
	
	@Query("select e from Exam e where e.startTime <> null")
	public Page<Exam> findAllReal(Pageable pageable);
	
	@Query("select e from Exam e where e.startTime = null and e.subjectId =:subjectId")
	public Page<Exam> findAllTestBySubjectId(Pageable pageable, @Param("subjectId") Integer subjectId);
	
	@Query("select e from Exam e where e.startTime <> null and e.subjectId =:subjectId")
	public Page<Exam> findAllRealBySubjectId(Pageable pageable, @Param("subjectId") Integer subjectId);
}
