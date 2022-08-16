package com.jyp.yoon.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Color {
	
	BLACK("검정"),
	WHITE("흰색"),
	IVORY("아이보리"),
	NAVY("네이비");
	
	final String korColor;
	
}
