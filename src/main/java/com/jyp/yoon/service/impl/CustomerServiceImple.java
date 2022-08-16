package com.jyp.yoon.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.jyp.yoon.domain.dto.customer.FaqInsertDTO;
import com.jyp.yoon.domain.dto.customer.FaqListDTO;
import com.jyp.yoon.domain.entity.Division;
import com.jyp.yoon.domain.entity.FaqEntityRepository;
import com.jyp.yoon.service.CustomerService;

@Service
public class CustomerServiceImple implements CustomerService {

	@Autowired
	FaqEntityRepository repository;
	
	@Override
	public void list(Model model, String div) {
		Division division= null;
		Division[] divs= Division.values();
		for(Division di :divs) {
			if(div.equals(di.getUrl())) {
				division=di;
				
			}
		}
		
		//div.use
		
		model.addAttribute("list",repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()));
	}

	@Override
	public void list(Model model, int divno) {
		Division division= null;
		Division[] divs= Division.values();
		for(Division di :divs) {
			if(divno==di.ordinal()) {
				division=di;
				
			}
	}
		model.addAttribute("list",repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()));
	}

	@Override
	public ModelAndView list(ModelAndView mv, int divno) {
		Division division= null;
		Division[] divs= Division.values();
		for(Division di :divs) {
			if(divno==di.ordinal()) {
				division=di;
				
			}
	}
		mv.addObject("list",repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()));
		mv.setViewName("/cus/faq/list");
		return mv;
	}
	//fnaq insert
	@Override
	public void save(Model model, FaqInsertDTO dto) {
		
	}
}

