package com.jyp.yoon.domain.dto.products;

import com.jyp.yoon.domain.entity.ProductEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderListDTO {
	
	private long prodNo;
	private String prodName;
	private int price;//판매가,저가
	private int sale; //할인가 or 할인율
	//private int stock;
	private int eaPrice;
	private int ea; //구매개수
	
	public OrderListDTO ea(int ea) {
		this.ea =ea;
		return this;
	}
	
	private String defImgUrl;
	
	//List<FileDTO> files;

	public OrderListDTO(ProductEntity e) {
		this.prodNo = e.getProdNo();
		this.prodName = e.getProdName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		//this.stock = e.getStock();
		this.eaPrice=e.getPrice()-getSale();
		e.getFiles().stream().forEach(fe->{
			if(fe.isDefImg()) defImgUrl =fe.getUrl()+fe.getOrgName();
		});
	}
	
	
}
