package com.jyp.yoon.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jyp.yoon.domain.dto.qna.QnaDetailDTO;
import com.jyp.yoon.domain.dto.qna.QnaInsertDTO;
import com.jyp.yoon.domain.dto.qna.QnaListDTO;
import com.jyp.yoon.domain.dto.qna.QnaUpdateDTO;
import com.jyp.yoon.domain.dto.qna.ReplyInsertDTO;
import com.jyp.yoon.domain.dto.qna.ReplyListDTO;
import com.jyp.yoon.domain.dto.qna.ReplyUpdateDTO;
import com.jyp.yoon.domain.entity.QnaEntity;
import com.jyp.yoon.domain.entity.QnaEntityRepository;
import com.jyp.yoon.domain.entity.ReplyEntityRepository;
import com.jyp.yoon.service.QnaService;
import com.jyp.yoon.utils.PageInfo;
@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaEntityRepository repository;
	
	@Autowired
	private ReplyEntityRepository replyEntityRepository;
	
	//list
	@Override
	public void list(Model model, int pageNo) {
		
		model.addAttribute("today", LocalDate.now());
		// TODO Auto-generated method stub
		int page = pageNo-1;
		int size = 10;
		Sort sort = Sort.by(Direction.DESC, "qnum");
		Pageable pageable = PageRequest.of(page, size, sort);
		
		Page<QnaEntity> pageObj = repository.findAll(pageable);
		int pageTotal = pageObj.getTotalPages();
		System.out.println(">>>>>>>>>>");
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("list",
				pageObj.getContent().stream().map(QnaListDTO::new).collect(Collectors.toList()));
	}

	//?????????
	@Override
	public void save(Model model, QnaInsertDTO dto) {
		repository.save(dto.toEntity());
		//Member member = mr.findByEmail(dto.getEmail())
	}

	//??????????????? ?????? ????????????
	
	@Transactional
	@Override
	public void detail(long qnum, Model model) {
		Optional<QnaEntity> result= repository.findById(qnum);
		model.addAttribute("detail", repository.findById(qnum).map(QnaDetailDTO::new).get());
		result.map(e->e.incrementReadCount());
	}
	
	//??????
	@Override
	public void update(long qnum, QnaUpdateDTO dto) {
		repository.findById(qnum).map(e->e.update(dto));
	}
	
	//??????
	@Override
	public void delete(long qnum) {
		repository.deleteById(qnum);
	}
	
	//????????? ????????? ?????? ??????
	@Override
	public boolean reply(ReplyInsertDTO dto) {
		replyEntityRepository.save(dto.toEntity());
		return true;
	}
	
	//?????? ????????????
	@Override
	public String replies(long qno, Model model) {
		
		model.addAttribute("today", LocalDate.now());
		
		List<ReplyListDTO> result = replyEntityRepository.findAllByQnaEntityQnum(qno, Sort.by(Direction.DESC, "rno"))
				.stream().map(ReplyListDTO::new).collect(Collectors.toList());
		model.addAttribute("list", result);
		return "cus/qna/reply/list";
	}
	
	//?????? ??????
	@Override
	public void updateRe(long qno, long rno, ReplyUpdateDTO dto) {
		
	}
	//?????? ??????
	@Override
	public void deleteRe(long qno, long rno) {
		repository.deleteById(rno);
	}

}
