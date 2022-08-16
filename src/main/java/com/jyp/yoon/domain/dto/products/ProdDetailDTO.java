package com.jyp.yoon.domain.dto.products;

import java.util.List;
import java.util.stream.Collectors;

import com.jyp.yoon.domain.entity.ProductEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProdDetailDTO {
	
	private long prodNo;
	private String prodName;
	private int price;//판매가,저가
	private int sale; //할인가 or 할인율
	private int stock ; 
	private String content;
	
	private List<FileDTO> files;
	//List<FileEntity>->List<FileDTO>
	public ProdDetailDTO(ProductEntity e) {
		this.prodNo = e.getProdNo();
		this.prodName = e.getProdName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		this.stock = e.getStock();
		this.content=e.getContent();
		this.files=e.getFiles().stream()
				.map(FileDTO::new)
				.collect(Collectors.toList());
	}
	
	
}
