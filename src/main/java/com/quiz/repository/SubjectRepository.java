package com.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
