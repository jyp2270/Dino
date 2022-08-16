package com.jyp.yoon.domain.dto.products;

import com.jyp.yoon.domain.entity.Category;
import com.jyp.yoon.domain.entity.ProductEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ProdInsertDTO {

	private String prodName;
	private int price;
	private int sale;
	private boolean rate;//false: 원, true: %
	private int stock;
	private String defImgName;
	private String addImgName;
	private String content;
	
	private long[] caNo;
	
	public ProductEntity toEntity() {
		if(rate) {
			sale=price*sale/100;
		}
		return ProductEntity.builder()
				.prodName(prodName).price(price).stock(stock).sale(sale).content(content)
				.build();
	}
}
