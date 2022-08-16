package com.jyp.yoon.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Division {
	
	MEMBER("회원 및 멤버십","members"),
	POINT("포인트","points"),
	DANDR("배송 및 환불","dandr"),
	FABRIC("세탁 및 옷감 정보","fabrics")
	;
	
	final String title;
	final String url;
	
}
