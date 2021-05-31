package com.quiz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer questionId;
	private String questionCode;
	private String question;
	private String answer_1;
	private String answer_2;
	private String answer_3;
	private String answer_4;
	private String answer_correct;
	private Integer level;
	private Integer subjectId;
	private Integer createBy;
	private Date createDate;
	private Integer updateBy;
	private Date updateDate;
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer_1() {
		return answer_1;
	}
	public void setAnswer_1(String answer_1) {
		this.answer_1 = answer_1;
	}
	public String getAnswer_2() {
		return answer_2;
	}
	public void setAnswer_2(String answer_2) {
		this.answer_2 = answer_2;
	}
	public String getAnswer_3() {
		return answer_3;
	}
	public void setAnswer_3(String answer_3) {
		this.answer_3 = answer_3;
	}
	public String getAnswer_4() {
		return answer_4;
	}
	public void setAnswer_4(String answer_4) {
		this.answer_4 = answer_4;
	}
	public String getAnswer_correct() {
		return answer_correct;
	}
	public void setAnswer_correct(String answer_correct) {
		this.answer_correct = answer_correct;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
