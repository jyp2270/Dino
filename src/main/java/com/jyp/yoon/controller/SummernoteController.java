package com.jyp.yoon.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SummernoteController {

	@ResponseBody
	@PostMapping("/admin/uploadSummernoteImg")
	public String uploadSummernoteImg(MultipartFile file) {
		// System.out.println(file.getOriginalFilename());
		// System.out.println(file.getContentType());

		if (!file.getContentType().contains("image"))
			return null;

		String url = "/images/summernote/";
		ClassPathResource cpr = new ClassPathResource("static" + url);
		String orgName = file.getOriginalFilename();
		String saveName = UUID.randomUUID() + "_" + orgName;
		// System.out.println(saveName);

		try {
			File location = cpr.getFile();
			file.transferTo(new File(location, saveName));
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}return url+saveName;
	}
}
