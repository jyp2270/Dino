package com.jyp.yoon.domain.dto.qna;

import java.time.LocalDateTime;

import com.jyp.yoon.domain.entity.QnaEntity;

import lombok.Getter;

@Getter
public class QnaDetailDTO {

	private long qnum;
	private String title;
	private String content;
	private String writer;
	private int readCount;
	private LocalDateTime createdDate;
	
	public QnaDetailDTO(QnaEntity e) {
		qnum=e.getQnum();
		title=e.getTitle();
		content=e.getContent();
		writer=e.getMember().getEmail();
		readCount=e.getReadCount();
		createdDate=e.getCreatedDate();
		
	}
}
