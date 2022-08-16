package com.jyp.yoon.domain.dto.qna;

import java.time.LocalDateTime;

import com.jyp.yoon.domain.entity.ReplyEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyUpdateDTO {
	private String text;
	private LocalDateTime updatedDate;
	
	public ReplyUpdateDTO(ReplyEntity e) {
		this.text = e.getText();
		this.updatedDate = e.getUpdatedDate();
	}
}
