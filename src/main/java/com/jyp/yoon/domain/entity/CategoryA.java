package com.jyp.yoon.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CategoryA {
	
	NEW("신상",1100,"NEW"),
	BEST("베스트",1200,"BEST"),
	SET("코디세트",1300,"SET"),
	ONEONE("1+1",1400,"1+1"),
	SUMMER("여름",1500,"SUMMER"),
	OUTER("아우터",1600,"OUTER"),
	TOP("상의",1700,"TOP"),
	BOTTOM("바지",1800,"BOTTOM"),
	OPS("원피스",1900,"OPS"),
	SKIRT("치마",2000,"SKIRT"),
	ACCBAG("악세사리,가방",2100,"ACC&BAG"),
	SHOES("신발",2200,"SHOES"),
	SALE("할인상품",2300,"SALE"),
	ROCKET("당일배송",2400,"ROCKET");
	
	final String koName;
	final long code;
	final String title;
	
}
