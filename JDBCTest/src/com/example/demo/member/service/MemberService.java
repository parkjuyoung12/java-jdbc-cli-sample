package com.example.demo.member.service;

import com.example.demo.member.dao.JoinResultState;
import com.example.demo.member.dto.MemberDto.LoginResponseDto;

public interface MemberService {
	
	JoinResultState join(String username, String password, String nickname);
	LoginResponseDto login(String username, String password);

}
