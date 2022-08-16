package com.jyp.yoon.domain.dto.customer;

import com.jyp.yoon.domain.entity.Division;
import com.jyp.yoon.domain.entity.FaqEntity;

import lombok.Getter;

@Getter

public class FaqListDTO {
	private long faqNo;
	
	private String question; //질문
	private String answer; //응답
	
	private Division division;
	
	public FaqListDTO(FaqEntity e) {
		faqNo = e.getFaqNo();
		question = e.getQuestion();
		answer= e.getAnswer();
		division= e.getDivision();
	}
}
