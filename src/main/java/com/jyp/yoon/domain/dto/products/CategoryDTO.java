package com.jyp.yoon.domain.dto.products;

import com.jyp.yoon.domain.entity.Category;

import lombok.Getter;

@Getter
public class CategoryDTO {
	
	private long caNo;
	private String name;
	private long code;
	
	public CategoryDTO(Category e) {
		this.caNo = e.getCaNo();
		this.name = e.getName();
		this.code = e.getCode();
		
	}
}
