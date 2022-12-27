package com.example.demo.member.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.example.demo.member.domain.LoginResultState;

public final class MemberDto {

	public static final class LoginRequestDto implements Serializable {
		private String username;
		private String password;
		
		public LoginRequestDto(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}
	}
	
	public static final class LoginResponseDto implements Serializable {
		private MemberVo member;
		private LoginResultState state;
		
		public LoginResponseDto(MemberVo member, LoginResultState state) {
			super();
			this.member = member;
			this.state = state;
		}
		
		public LoginResponseDto(Long id, String username, String nickname, OffsetDateTime joindate, LoginResultState state) {
			super();
			this.member = new MemberVo(id, username, nickname, joindate);
			this.state = state;
		}

		public MemberVo getMember() {
			return member;
		}

		public LoginResultState getState() {
			return state;
		}
	}
}
