package com.jyp.yoon.domain.dto.products;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ProdUpdateDto {
	private String prodName;
	private int price;
	private int sale;
	private int stock;
}
