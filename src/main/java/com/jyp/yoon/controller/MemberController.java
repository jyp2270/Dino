package com.jyp.yoon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.yoon.service.MailService;
import com.jyp.yoon.service.MemberService;
import com.jyp.yoon.domain.dto.member.MemberInsertDTO;

import lombok.RequiredArgsConstructor;

@Controller
public class MemberController {
	
	
	@Autowired
	private MemberService service;
	@Autowired
	private MailService mailService;
	
	@PostMapping("/common/signup")
	public String signup(MemberInsertDTO dto, HttpServletRequest request) {
		System.out.println("회원가입처리");
		return service.save(dto, request);
	}
	
	@ResponseBody
	@PostMapping("/mailsend")
	public String requestMailKey(String email) {
		System.out.println("mail...>>1");
		return mailService.mailSend(email);
	}
	@ResponseBody
	@GetMapping("/request-key/getKey")
	public String requestMailKey(HttpSession session) {
		
		System.out.println("마지막 접속시간 :"+session.getLastAccessedTime());
		System.out.println("생성시간 :"+session.getCreationTime());
		System.out.println("유지시간 :" +session.getMaxInactiveInterval());
		//System.out.println(email);
		return (String) session.getAttribute("mailKey");
	}
	
	@ResponseBody
	@GetMapping("/request-key/remove")
	public void requestRemove(HttpSession session) {
		session.invalidate();
	}
}
