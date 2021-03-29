package com.quiz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "History")
public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer historyId;
	private String examCode;
	private String accountCode;
	private Date testDay;
	private float result;
	
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public Date getTestDay() {
		return testDay;
	}
	public void setTestDay(Date testDay) {
		this.testDay = testDay;
	}
	
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public float getResult() {
		return result;
	}
	public void setResult(float result) {
		this.result = result;
	}
	public History() {
		super();
	}
	public History(Integer historyId, String examCode, String accountCode, Date testDay, float result) {
		super();
		this.historyId = historyId;
		this.examCode = examCode;
		this.accountCode = accountCode;
		this.testDay = testDay;
		this.result = result;
	}
	
	
}
