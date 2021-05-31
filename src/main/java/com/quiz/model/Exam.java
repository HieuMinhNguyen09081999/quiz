package com.quiz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Exam")
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examId;
	private String examCode;
	private Integer subjectId;
	private Integer duration;
	private Integer totalAmountQuestion;
	private Integer amountQuestionHard;
	private Integer amountQuestionNormal;
	private Integer amountQuestionEasy;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startTime;
	private Integer createBy;
	private Date createDate;
	private Integer updateBy;
	private Date updateDate;

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getTotalAmountQuestion() {
		return totalAmountQuestion;
	}

	public void setTotalAmountQuestion(Integer totalAmountQuestion) {
		this.totalAmountQuestion = totalAmountQuestion;
	}

	public Integer getAmountQuestionHard() {
		return amountQuestionHard;
	}

	public void setAmountQuestionHard(Integer amountQuestionHard) {
		this.amountQuestionHard = amountQuestionHard;
	}

	public Integer getAmountQuestionNormal() {
		return amountQuestionNormal;
	}

	public void setAmountQuestionNormal(Integer amountQuestionNormal) {
		this.amountQuestionNormal = amountQuestionNormal;
	}

	public Integer getAmountQuestionEasy() {
		return amountQuestionEasy;
	}

	public void setAmountQuestionEasy(Integer amountQuestionEasy) {
		this.amountQuestionEasy = amountQuestionEasy;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
