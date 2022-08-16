package com.jyp.yoon.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jyp.yoon.domain.entity.Member;

import lombok.Setter;

@Setter
public class MemberInsertDTO {
	private String email;
	private String name;
	private String pass;
	private String userIp;
	
	public MemberInsertDTO passEncode(PasswordEncoder pe) {
		this.pass=pe.encode(pass);
		return this;
	}
	
	//입력받은 dto-> Member 엔티티에 매핑: Member 객체생성
	public Member toMember() {
		return Member.builder()
				.email(email).name(name).pass(pass).userIp(userIp)
				.build();
	}
}
