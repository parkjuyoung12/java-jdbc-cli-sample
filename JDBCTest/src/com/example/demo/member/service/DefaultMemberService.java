package com.example.demo.member.service;

import com.example.demo.member.dao.JoinResultState;
import com.example.demo.member.dao.MemberDao;
import com.example.demo.member.dto.MemberVo;
import com.example.demo.member.dto.MemberDto.LoginRequestDto;
import com.example.demo.member.dto.MemberDto.LoginResponseDto;

public class DefaultMemberService implements MemberService {

	private final MemberDao memberDao = new MemberDao();
	
	@Override
	public JoinResultState join(String username, String password, String nickname) {
		MemberVo member = new MemberVo(username, password, nickname);
		JoinResultState joinState = memberDao.join(member);
		return joinState;
	}
	
	@Override
	public LoginResponseDto login(String username, String password) {
		LoginRequestDto member = new LoginRequestDto(username, password);
		LoginResponseDto result = memberDao.login(member);
		
		return result;
	}
}
