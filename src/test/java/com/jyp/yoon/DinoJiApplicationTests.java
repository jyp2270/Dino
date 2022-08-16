package com.jyp.yoon;

import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.jyp.yoon.domain.entity.Category;
import com.jyp.yoon.domain.entity.CategoryA;
import com.jyp.yoon.domain.entity.CategoryRepository;
import com.jyp.yoon.domain.entity.Division;
import com.jyp.yoon.domain.entity.FaqEntity;
import com.jyp.yoon.domain.entity.FaqEntityRepository;
import com.jyp.yoon.domain.entity.Member;
import com.jyp.yoon.domain.entity.MemberRepository;
import com.jyp.yoon.domain.entity.ProductEntity;
import com.jyp.yoon.domain.entity.ProductEntityRepository;
import com.jyp.yoon.security.MemberRole;

@SpringBootTest
class DinoJiApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	FaqEntityRepository faqEntityRepository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	CategoryRepository caRepository;
	
	@Autowired
	ProductEntityRepository productRepository;
	
	@Commit
	@Transactional
	@Test
	void 카테고리_연결() {
		ProductEntity entity = productRepository.findById(1L).get();
		Category category = caRepository.findById(2401L).get();
		productRepository.save(entity.addCategory(Category.builder()
						.caNo(2401)
						.build()));
	}
	
	//@Test
	void 카테고리_생성() {
		CategoryA cateA=CategoryA.SUMMER;
		long count=caRepository.countByCateA(cateA);
		
		//System.out.println("max: "+count);
		
		caRepository.save(Category.builder()
				.name("SUMMER")
				.code(++count)
				.cateA(cateA)
				.build().creatNo());
	}
	
	//@Test
	void 테스트데이터() {
		
		IntStream.rangeClosed(1, 10).forEach(i->{
			
		Division div=Division.POINT;
			
		faqEntityRepository.save(FaqEntity.builder()
				.question(div.name()+"질문"+i).answer(div.name()+"답변"+i).division(div)
				.build());
		});
	}
	
	//@Test
	void 관리자생성() {
		memberRepository.save(Member.builder()
				.email("admin").pass(pe.encode("1111")).name("관리자").userIp("127.0.0.1")
				.build().addRole(MemberRole.ADMIN).addRole(MemberRole.USER));
	}
	
	
	//@Test
	void 회원생성() {
		memberRepository.save(Member.builder()
				.email("test01@test.com").pass(pe.encode("1111")).name("테스트01").userIp("127.0.0.1")
				.build().addRole(MemberRole.USER));
	}
	
	
	//insert into member 
	//(created_date, updated_date, email, is_deleted, name, pass, phone, user_ip)
	// values (?, ?, ?, ?, ?, ?, ?, ?)
	//insert into member 
	//(created_date, updated_date, email, is_deleted, name, pass, user_ip) 
	// values (?, ?, ?, ?, ?, ?, ?)
//	@Rollback(value =false )
//	@Transactional
//	//@Test
//	void 회원수정테스트() {
//		
//		System.out.println("-------------------");
//		//Member entity2=entity.pass("2222");
//		//Member entity3=entity2.name("이름변경");
//		//Hibernate: update member set created_date=?, deleted_date=?, email=?, is_deleted=?, name=?, pass=?, updated_date=?, user_ip=? where mno=?
//		//update member set name=?, pass=?, updated_date=? where mno=?
//		//memberRepository.save(entity);
//	}
}
