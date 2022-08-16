package com.jyp.yoon.domain.dto.products;

import com.jyp.yoon.domain.entity.Cart;

import lombok.Getter;

@Getter
public class CartListDTO {
	
	private long no; //카트pk
	private int ea;//수량
	private ProdListDTO goods;
	
	public CartListDTO(Cart e) {
		this.no = e.getNo();
		this.ea = e.getEa();
		this.goods = new ProdListDTO(e.getProds());
	}
	
	

}
