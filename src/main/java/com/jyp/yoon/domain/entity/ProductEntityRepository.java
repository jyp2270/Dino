package com.jyp.yoon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long>{

	List<ProductEntity> findAllByCategoriesCaNo(long caNo);

	List<ProductEntity> findAllByCategoriesCaNoBetween(long caNo, long l);

	Optional<ProductEntity> findByProdNoAndStockGreaterThanEqual(long prodNo, int ea);

}
