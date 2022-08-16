package com.jyp.yoon.domain.dto.qna;

import com.jyp.yoon.domain.entity.QnaEntity;
import com.jyp.yoon.domain.entity.ReplyEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyInsertDTO {
	private long qno;
	private String replier;
	private String text;
	
	public ReplyEntity toEntity() {
		return ReplyEntity.builder()
				.replier(replier).text(text)
				.qnaEntity(QnaEntity.builder().qnum(qno).build())
				.build();
	}
}
