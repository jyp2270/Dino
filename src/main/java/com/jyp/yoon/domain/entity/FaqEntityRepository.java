package com.jyp.yoon.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqEntityRepository extends JpaRepository<FaqEntity, Long> {

	List<FaqEntity> findAllByDivision(Division division);

}
