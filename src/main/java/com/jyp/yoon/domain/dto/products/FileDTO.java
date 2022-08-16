package com.jyp.yoon.domain.dto.products;

import com.jyp.yoon.domain.entity.FileEntity;

import lombok.Getter;

@Getter
public class FileDTO {
	
	private long no;
	private String url;
	private String orgName;
	private String newName;
	private boolean isDefImg;
	private long size;
	
	public FileDTO(FileEntity e) {
		this.no = e.getNo();
		this.url = e.getUrl();
		this.orgName = e.getOrgName();
		this.newName = e.getNewName();
		this.isDefImg = e.isDefImg();
		this.size = e.getSize();
	}
	
	
	
}
