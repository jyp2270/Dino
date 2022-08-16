package com.jyp.yoon.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Category {
	
	@Id
	private long caNo;
	
	public Category creatNo() {
		caNo = cateA.code +code;
		return this;
	}
	
	private String name;
	private long code;
	
	@Enumerated(EnumType.STRING)
	private CategoryA cateA; //상위카테고리
	
	@Builder.Default
	@ManyToMany(mappedBy = "categories")
	Set<ProductEntity> products = new HashSet<>();
	
	
}
