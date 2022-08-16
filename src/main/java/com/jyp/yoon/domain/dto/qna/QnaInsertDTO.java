package com.jyp.yoon.domain.dto.qna;


import com.jyp.yoon.domain.entity.Member;
import com.jyp.yoon.domain.entity.QnaEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaInsertDTO {
	private String title;
	private String content;
	private long mno;
	
	public QnaEntity toEntity() {
		return QnaEntity.builder().title(title).content(content)
				.member(Member.builder().mno(mno).build()).build();
	}

}
