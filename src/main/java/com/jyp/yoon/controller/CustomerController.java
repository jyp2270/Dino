package com.jyp.yoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jyp.yoon.domain.dto.customer.FaqInsertDTO;
import com.jyp.yoon.domain.dto.qna.QnaInsertDTO;
import com.jyp.yoon.service.CustomerService;

@RestController //Controller + @ResponseBody
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	/*
	//고객센터에 최초 로딩시 :faq의 사이트 이용내용만 가지고 올거예요
	@GetMapping("/customer/{divName}")
	public String list(Model model, @PathVariable String divName) {
		
		service.list(model,divName);
		return "cus/faq/list";
	}
	*/
	

	//url주소에 숫자로 요청하는 case
	@GetMapping("/faq/{divno}")
	public ModelAndView list(ModelAndView mv, @PathVariable int divno) {
		
		return service.list(mv,divno);
	}
	
	//페이지 이동 위해 mv사용한 case
	@GetMapping("/faq")
	public ModelAndView list(ModelAndView mv) {
		mv.setViewName("cus/faq/index");
		return mv;
	}
	

	@PostMapping("/faq/write")
	public String write(Model model,FaqInsertDTO dto) {
		service.save(model,dto);
		return "redirect:/faq/list";
	}
}