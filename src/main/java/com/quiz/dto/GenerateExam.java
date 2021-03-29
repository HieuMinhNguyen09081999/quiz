package com.quiz.dto;

public class GenerateExam {
	private Integer subjectId;
	private Integer duration;
	private Integer totalQuestionEasy;
	private Integer totalQuestionNormal;
	private Integer totalQuestionHard;
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
	public Integer getTotalQuestionEasy() {
		return totalQuestionEasy;
	}
	public void setTotalQuestionEasy(Integer totalQuestionEasy) {
		this.totalQuestionEasy = totalQuestionEasy;
	}
	public Integer getTotalQuestionNormal() {
		return totalQuestionNormal;
	}
	public void setTotalQuestionNormal(Integer totalQuestionNormal) {
		this.totalQuestionNormal = totalQuestionNormal;
	}
	public Integer getTotalQuestionHard() {
		return totalQuestionHard;
	}
	public void setTotalQuestionHard(Integer totalQuestionHard) {
		this.totalQuestionHard = totalQuestionHard;
	}
	
	
}
