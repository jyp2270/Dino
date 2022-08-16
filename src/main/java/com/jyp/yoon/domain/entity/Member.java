package com.jyp.yoon.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.jyp.yoon.security.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert//insert시 Entity에 null데이터는 퀄리에 적용시키지 않아요
@DynamicUpdate//실제로 update처리하는 컬럼만 쿼리에 적용된다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long mno;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String pass;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String userIp;
	
	private boolean isDeleted;//default:false 탈퇴여부 //true:탈퇴신청한회원
	
	private boolean isSocial;
	
	
	@Builder.Default
	@Enumerated(EnumType.STRING)//DB에 저장시 문자열로 저장할때 적용
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MemberRole> roleSet=new HashSet<>();
	
	@Builder.Default
	@OneToMany(mappedBy = "member")
	List<QnaEntity> qnas=new ArrayList<QnaEntity>();

	public Member addRole(MemberRole role) {
		roleSet.add(role);
		return this;
	}
	public Member removeRole(MemberRole role) {
		roleSet.remove(role);
		return this;
	}
	public Member socialUpdate(String name,String pass) {
		this.name=name;
		this.pass=pass;
		return this;
	}
	
	

}
