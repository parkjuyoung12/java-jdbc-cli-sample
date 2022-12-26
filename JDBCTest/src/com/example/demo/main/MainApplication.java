package com.example.demo.main;

import java.util.Scanner;

import com.example.demo.main.ui.PathCursor;
import com.example.demo.member.dao.JoinResultState;
import com.example.demo.member.dto.MemberVo;
import com.example.demo.member.service.DefaultMemberService;
import com.example.demo.member.service.MemberService;

// UI 분리 안 함. 여기서 수행.
public class MainApplication {
	
	private final Scanner scan = new Scanner(System.in);
	private MemberVo loginMember;
	private PathCursor cursor = PathCursor.HOME;
	
	private final MemberService memberService = new DefaultMemberService();
	
	public void run() {
		mainLoop: while(true) {	
			if(cursor == PathCursor.HOME) {
				home();
			} else if(cursor == PathCursor.JOIN) {
				join();
			} else if(cursor == PathCursor.QUIT) {
				return;
			}
		} // while(true)
	} // void run();
	
	private void home() {
		System.out.println("===========================");
		System.out.println("=========== HOME ==========");
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 회원가입");
		System.out.println(" 2. 종료");
		int number = scan.nextInt();
		scan.nextLine(); // flush
		
		switch (number) {
			case 1:
				this.cursor = PathCursor.JOIN;
				break;
			case 2:
				this.cursor = PathCursor.QUIT;
				break;
			default:
				break;
		}
		
		return;
	}
	
	private void join() {
		String username;
		String password;
		String nickname;
		
		System.out.println("===========================");
		System.out.println("=========== JOIN ==========");
		System.out.println("아이디를 작성하세요.");
		username = scan.nextLine();
		System.out.println("비밀번호를 작성하세요.");
		password = scan.nextLine();
		System.out.println("닉네임을 작성하세요.");
		nickname = scan.nextLine();
		
		JoinResultState joinState = memberService.join(username, password, nickname);
		if(joinState == JoinResultState.SUCCESS) {
			System.out.println("회원가입 성공");
			this.cursor = PathCursor.HOME;
		} else if(joinState == JoinResultState.USERNAME_DUPLICATED) {
			System.out.println("중복됩니다.");
			this.cursor = PathCursor.JOIN;
		} else if(joinState == JoinResultState.CONNECTION_ERROR) {
			System.out.println("연결문제");
			this.cursor = PathCursor.HOME;
		}
	}
}
