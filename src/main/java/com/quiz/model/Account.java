package com.quiz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Account")
public class Account extends Common {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	private String accountCode;
	private String fullName;
	private Integer gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthOfDate;
	private String phoneNumber;
	private String email;
	private String password;
	private Integer role;
	private String avtPath;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthOfDate() {
		return birthOfDate;
	}

	public void setBirthOfDate(Date birthOfDate) {
		this.birthOfDate = birthOfDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getAvtPath() {
		return avtPath;
	}

	public void setAvtPath(String avtPath) {
		this.avtPath = avtPath;
	}

	public Account(Integer createBy, Date createDate, Integer updateBy, Date updateDate, Integer accountId,
			String accountCode, String fullName, Integer gender, Date birthOfDate, String phoneNumber, String email, String password, Integer role, String avtPath) {
		super(createBy, createDate, updateBy, updateDate);
		this.accountId = accountId;
		this.accountCode = accountCode;
		this.fullName = fullName;
		this.gender = gender;
		this.birthOfDate = birthOfDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.role = role;
		this.avtPath = avtPath;
	}

	public Account() {
		super();
	}

}
