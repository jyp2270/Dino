package com.jyp.yoon.domain.dto.products;


import com.jyp.yoon.domain.entity.Cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartInsertDto {
	
	private long prodNo;
	private int ea;
	private String email;
	
	public CartInsertDto email(String email) {
		this.email=email;
		return this;
	}

	public Cart toEntity() {
		
		return Cart.builder()
				.ea(ea)
				.build();
	}

}
