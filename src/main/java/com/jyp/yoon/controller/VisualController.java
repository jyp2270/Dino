package com.jyp.yoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jyp.yoon.domain.dto.visual.VisualInsertDTO;
import com.jyp.yoon.domain.dto.visual.VisualUpdateDto;
import com.jyp.yoon.service.VisualService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class VisualController {
	
	@Autowired
	private VisualService visualService;
	
	//index페이지 ajax에서 요청한 메서드
	//등록된 이미지 목록
	@GetMapping("/common/visuals")
	public String indexList(Model model) {
		return visualService.indexList(model);
	}
	
	//관리자 페이지에 등록된 이미지 목록
	@GetMapping("/admin/visuals")
	public String list(Model model) {
		return visualService.list(model);
	}
	
	@ResponseBody
	@PutMapping("/admin/visuals/{vno}")
	public boolean updateData(@PathVariable long vno,  VisualUpdateDto dto) {
		log.info("@PutMapping 처리 ajax에서는 _method가 없는데 처리되요?");
		System.out.println(dto);
		return visualService.updateData(vno, dto);
	}
	
	@ResponseBody
	@PutMapping("/admin/visuals/{vno}/isShow")
	public boolean updateData(@PathVariable long vno,  boolean isShow) {
		log.info("@PutMapping 처리 ajax에서는 _method가 없는데 처리되요?");
		return visualService.updateIsShow(vno, isShow);
	}
	
	@ResponseBody
	@DeleteMapping("/admin/visuals/{vno}")
	public boolean updateData(@PathVariable long vno) {
		System.out.println(vno);
		return visualService.delete(vno);
	}
	
	
	
	//등록페이지이동
	@GetMapping("/admin/visuals/write")
	public String write() {
		return "/admin/visual/write";
	}
	
	
	
	//등록 처리
	@PostMapping("/admin/visuals")
	public String save(MultipartFile vimg, VisualInsertDTO dto) {
		return visualService.save(vimg, dto);
	}

	
	
}
