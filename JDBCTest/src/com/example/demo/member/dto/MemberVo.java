package com.example.demo.member.dto;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MemberVo {

	private Long id;
	private String username;
	private String password;
	private String nickname;
	private OffsetDateTime joindate;
	
	public MemberVo(String username, String password, String nickname) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.joindate = OffsetDateTime.now(ZoneId.of("Asia/Tokyo"));
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public OffsetDateTime getJoindate() {
		return joindate;
	}
	
	public String getFormattedJoindate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		
		return formatter.format(this.joindate);
	}
	
	@Override
	public String toString() { 
		return String.format("MemberVo{id=%d, username=%s, password=%s, nickname=%s, joindate=%s}",
				id,
				username,
				password,
				nickname,
				getFormattedJoindate());
	}

	
	
}