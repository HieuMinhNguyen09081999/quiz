package com.quiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ExamQuestion")
public class ExamQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examQuestionId;
	private String examCode;
	private String questionCode;
	
	public Integer getExamQuestionid() {
		return examQuestionId;
	}
	public void setExamQuestionid(Integer examQuestionid) {
		this.examQuestionId = examQuestionid;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public ExamQuestion(Integer examQuestionid, String examCode, String questionCode) {
		super();
		this.examQuestionId = examQuestionid;
		this.examCode = examCode;
		this.questionCode = questionCode;
	}
	public ExamQuestion() {
		super();
	}
	
	
}
