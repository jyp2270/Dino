package com.jyp.yoon.domain.dto.qna;

import java.time.LocalDateTime;

import com.jyp.yoon.domain.entity.QnaEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class QnaListDTO {
	private long qnum;
	private String title;
	private String writer;
	private int readCount;
	private LocalDateTime createdDate;
	
	public QnaListDTO(QnaEntity e) {
		qnum=e.getQnum();
		title=e.getTitle();
		writer=e.getMember().getEmail();
		readCount=e.getReadCount();
		createdDate=e.getCreatedDate();
		
	}
}
