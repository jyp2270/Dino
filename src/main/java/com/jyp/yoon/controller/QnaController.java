package com.jyp.yoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.yoon.domain.dto.qna.QnaInsertDTO;
import com.jyp.yoon.domain.dto.qna.QnaUpdateDTO;
import com.jyp.yoon.domain.dto.qna.ReplyInsertDTO;
import com.jyp.yoon.domain.dto.qna.ReplyUpdateDTO;
import com.jyp.yoon.service.QnaService;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService service ;

	//qna게시판으로 이동 --list 나옴
	@GetMapping("/qna/qnaList")
	public String list(Model model,@RequestParam(defaultValue = "1") int pageNo) {
		
		service.list(model,pageNo);
		return "cus/qna/qnaList";
	}
	//qna 글쓰기 페이지
	@GetMapping("/qna/write")
	public String write() {
		return "cus/qna/qnaWrite";
	}
	//글쓰기 버튼 눌렀을 때 (db저장)
	@PostMapping("/qna/write")
	public String write(Model model,QnaInsertDTO dto) {
		service.save(model,dto);
		return "redirect:/qna/qnaList";
	}
	//상세화면
	@GetMapping("/qna/{qnum}")
	public String detail(@PathVariable long qnum, Model model) {
		service.detail(qnum,model);
		return "cus/qna/qnaDetail";
	}
	
	//수정
	@PutMapping("/qna/{qnum}")
	public String update(@PathVariable long qnum, QnaUpdateDTO dto) {
		service.update(qnum, dto);
		return "redirect:/qna/"+qnum;
	}
	//삭제
	@ResponseBody
	@DeleteMapping("/qna/{qnum}")
	public void delete(@PathVariable long qnum) {
		service.delete(qnum);
	}
	
	//댓글 달기
	@ResponseBody
	@PostMapping("/qna/{qno}/reply")
	public boolean reply(ReplyInsertDTO dto) {
		return service.reply(dto);
	}
	
	//댓글 읽어오기
	@GetMapping("/qna/{qno}/replies")
	public String replies(@PathVariable long qno,Model model) {
		return service.replies(qno, model);
	}
	
	//댓글수정
	@PutMapping("/qna/{qno}/replies/{rno}")
	public void update(@PathVariable long qno,@PathVariable long rno, ReplyUpdateDTO dto) {
		service.updateRe(qno, rno, dto);
	}
	
	//댓글삭제
	@ResponseBody
	@DeleteMapping("/qna/{qno}/replies/{rno}")
	public void deleteRe(@PathVariable long qno,@PathVariable long rno) {
		service.deleteRe(qno,rno);
		}
}
