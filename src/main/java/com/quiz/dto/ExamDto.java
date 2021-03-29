package com.quiz.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ExamDto {
	private Integer examId;
	private String examCode;
	private Integer subjectId;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startTime;
	private Integer duration;
	private List<String> listQuestion;

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

	public List<String> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<String> listQuestion) {
		this.listQuestion = listQuestion;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
