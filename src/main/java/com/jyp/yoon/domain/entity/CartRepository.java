package com.jyp.yoon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	List<Cart> findAllByMemberEmail(String email);

	Optional<Cart> findByProdsProdNo(long prodNo);

}
