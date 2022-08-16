package com.jyp.yoon.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long > {
	
	
	long countByCateA(CategoryA cateA);

	List<Category> findByCaNoBetween(long caNo, long l);

	List<Category> findByCateA(CategoryA catea);
}
