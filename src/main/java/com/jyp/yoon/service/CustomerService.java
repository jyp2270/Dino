package com.jyp.yoon.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.jyp.yoon.domain.dto.customer.FaqInsertDTO;


public interface CustomerService {


	void list(Model model, String divName);
	
	void list(Model model, int divno);

	ModelAndView list(ModelAndView mv, int divno);

	void save(Model model, FaqInsertDTO dto);

}
