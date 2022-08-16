package com.jyp.yoon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/signin")
	public String login(HttpServletRequest request) {
		String uri = request.getHeader("Referer");
		System.out.println("Referer:"+uri);
	    if (uri != null && !uri.contains("/signin")) {
	        request.getSession().setAttribute("prevPage", uri);
	    }
		return "sign/signin";
	}
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "redirect:/";
	}
	
	@GetMapping("/common/signup")
	public String signup() {
		return "sign/signup";
	}
}
