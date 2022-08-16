package com.jyp.yoon.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long>{

	List<ReplyEntity> findAllByQnaEntityQnum(long qno);
	List<ReplyEntity> findAllByQnaEntityQnumOrderByRnoDesc(long qno);
	List<ReplyEntity> findAllByQnaEntityQnum(long qno, Sort sort);
	
	Page<ReplyEntity> findAllByQnaEntityQnum(long qno, Pageable pageable);

}
