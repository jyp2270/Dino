package com.jyp.yoon.domain.dto.qna;

import java.time.LocalDateTime;

import com.jyp.yoon.domain.entity.ReplyEntity;

import lombok.Getter;

@Getter
public class ReplyListDTO {
	
	private long rno;
	private String text;
	private String replier;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public ReplyListDTO(ReplyEntity e) {
		this.rno = e.getRno();
		this.text = e.getText();
		this.replier = e.getReplier();
		this.createdDate = e.getCreatedDate();
		this.updatedDate = e.getUpdatedDate();
	}
	
}
