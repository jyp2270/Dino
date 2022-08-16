package com.jyp.yoon.service;

import javax.servlet.http.HttpServletRequest;

import com.jyp.yoon.domain.dto.member.MemberInsertDTO;

public interface MemberService {

	String save(MemberInsertDTO dto, HttpServletRequest request);


}
