package com.jyp.yoon.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.jyp.yoon.domain.dto.products.ProdUpdateDto;
import com.jyp.yoon.domain.dto.visual.VisualUpdateDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ProductEntity extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long prodNo;
	
	private String prodName;
	private int price;//판매가,저가
	private int sale; //할인가 or 할인율
	private int stock ; 
	@Column(columnDefinition = "text", nullable = false)
	private String content;
	private boolean isShow;
	
	//색상
	@Builder.Default
	@Enumerated(EnumType.STRING)
	//@CollectionTable(name ="colors")
	@ElementCollection
	Set<Color> colors = new HashSet<>();
	
	//사이즈
	@Builder.Default
	@Enumerated(EnumType.STRING)
	//@CollectionTable(name ="size")
	@ElementCollection
	Set<Size> size = new HashSet<>();
	
	//1:N 단방향 설정 연관테이블 생성시키지 않기 위해 join
	@Builder.Default
	@JoinColumn(name="prodNo") //fk 이름 설정 다쪽(N) entity에 생성 (files_gno) : default : products_entity_gno
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<FileEntity> files = new ArrayList<>();
	
	public ProductEntity addFile(FileEntity file) {
		files.add(file);
		return this;
	}
	
	@Builder.Default
	@JoinColumn //categories_ca_no
	@ManyToMany(cascade = CascadeType.DETACH)
	Set<Category> categories=new HashSet<>();
	//모든카테고리 비우기
	public ProductEntity removeCategoryAll() {
		categories.clear();
		return this;
	}
	//카테고리 추가하기
	public ProductEntity addCategory(Category category) {
		categories.add(category);
		return this;
	}
	public ProductEntity updateIsShow(boolean isShow) {
		this.isShow=isShow;
		return this;
	}

	public ProductEntity updateProdName(String prodName) {
		this.prodName=prodName;
		return this;
	}

	public ProductEntity updateStock(int stock) {
		this.stock=stock;
		return this;
	}
	public ProductEntity updateSale(int sale) {
		this.sale=sale;
		return this;
	}
	public ProductEntity updatePrice(int price) {
		this.sale=sale;
		return this;
	}

	public ProductEntity updateData(ProdUpdateDto dto) {
		this.prodName=dto.getProdName();
		this.price=dto.getPrice();
		this.sale=dto.getSale();
		this.stock=dto.getStock();
		return this;
	}
}
