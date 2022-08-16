package com.jyp.yoon.domain.dto.customer;

import com.jyp.yoon.domain.entity.Division;
import com.jyp.yoon.domain.entity.FaqEntity;

public class FaqInsertDTO {
	
	private String question; //질문
	private String answer; //응답
	
	private Division division;
	
	public FaqInsertDTO(FaqEntity e) {
		question = e.getQuestion();
		answer= e.getAnswer();
		division= e.getDivision();
	}
}
