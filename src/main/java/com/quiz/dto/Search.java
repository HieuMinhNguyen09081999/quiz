package com.quiz.dto;

public class Search {
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Search(String keyword) {
		super();
		this.keyword = keyword;
	}

	public Search() {
		super();
	}
	
	
}
