package com.jyp.yoon.domain.dto.products;

import com.jyp.yoon.domain.entity.ProductEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProdListDTO {
	
	private long prodNo;
	private String prodName;
	private int price;//판매가,저가
	private int sale; //할인가 or 할인율
	private int stock ; 
	private boolean isShow;
	
	private String defImgUrl;
	
	//List<FileDTO> files;

	public ProdListDTO(ProductEntity e) {
		this.prodNo = e.getProdNo();
		this.prodName = e.getProdName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		this.stock = e.getStock();
		//파일정보 :List<FileEntity> files -> List<FileDTO> files;
		//this.files = e.getFiles().stream()
		//		.map(FileDTO::new)
		//		.collect(Collectors.toList());
		
		e.getFiles().stream().forEach(fe->{
			if(fe.isDefImg()) defImgUrl =fe.getUrl()+fe.getOrgName();
		});
	}
	
	
}
