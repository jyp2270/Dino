package com.jyp.yoon.service;

import org.springframework.ui.Model;

import com.jyp.yoon.domain.dto.qna.QnaInsertDTO;
import com.jyp.yoon.domain.dto.qna.QnaUpdateDTO;
import com.jyp.yoon.domain.dto.qna.ReplyInsertDTO;
import com.jyp.yoon.domain.dto.qna.ReplyUpdateDTO;

public interface QnaService {

	void list(Model model, int pageNo);

	void save(Model model, QnaInsertDTO dto);

	void delete(long qnum);

	void detail(long qnum, Model model);

	boolean reply(ReplyInsertDTO dto);

	String replies(long qnum, Model model);

	void update(long qnum, QnaUpdateDTO dto);

	void updateRe(long qno, long rno, ReplyUpdateDTO dto);

	void deleteRe(long qno, long rno);
}
