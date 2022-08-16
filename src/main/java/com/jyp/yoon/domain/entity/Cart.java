package com.jyp.yoon.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Cart extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	private int ea;
	
	//ea를 변경(대체)
	public Cart updateEa(int ea) {
		this.ea=ea;
		return this;
	}
	//ea를 추가
	public Cart plusEa(int addEa) {
		ea+=addEa;
		return this;
	}
	
	
	//N:1 gno 상품테이블과 관계설정
	@JoinColumn(name = "prodNo")
	@ManyToOne(cascade = CascadeType.DETACH)
	private ProductEntity prods;
	
	@JoinColumn(name = "mno")//email
	@ManyToOne(cascade = CascadeType.DETACH)
	private Member member;

	//상품Entity를 세팅하기위한 편의 메서드
	public Cart goods(ProductEntity prods) {
		this.prods=prods;
		return this;
	}
	//회원Entity를 세팅하기위한 편의메서드
	public Cart member(Member member) {
		this.member=member;
		return this;
	}

	
	
	
}
