package com.jyp.yoon.domain.entity;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaEntityRepository extends JpaRepository<QnaEntity, Long>{


}
