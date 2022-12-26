package com.example.demo.main;

import java.util.Scanner;

import com.example.demo.board.domain.BoardAddResult;
import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.DefaultBoardService;
import com.example.demo.main.ui.PathCursor;
import com.example.demo.member.domain.JoinResultState;
import com.example.demo.member.domain.LoginResultState;
import com.example.demo.member.dto.MemberVo;
import com.example.demo.member.dto.MemberDto.LoginResponseDto;
import com.example.demo.member.service.DefaultMemberService;
import com.example.demo.member.service.MemberService;

// UI 분리 안 함. 여기서 수행.
public class MainApplication {
	
	private final Scanner scan = new Scanner(System.in);
	private MemberVo loginMember;
	private PathCursor cursor = PathCursor.HOME;
	
	private final MemberService memberService = new DefaultMemberService();
	private final BoardService boardService = new DefaultBoardService();
	
	public void run() {
		mainLoop: while(true) {
			switch(this.cursor) {
				case HOME:
					home();
					break;
					
				case JOIN:
					join();
					break;
					
				case LOGIN:
					login();
					break;
					
				case LOGOUT:
					logout();
					break;
					
				case BOARD_HOME:
					boardHome();
					break;
					
				case BOARD_CREATE:
					boardAddForm();
					break;
					
				case QUIT:
					System.out.println("종료합니다.");
					return;
					
				default:
					break;
			} // switch(cursor)
		} // while(true)
	} // void run();
	
	private void home() {
		System.out.println("===========================");
		System.out.println("=========== HOME ==========");
		
		if(loginMember != null) {
			homeAuthorized();
		} else {
			homeUnauthorized();
		}
		
		return;
	}

	private void homeAuthorized() {
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 게시판 가기");		
		System.out.println(" 9. 로그아웃");
		System.out.println(" 0. 종료");
		System.out.print("> ");
		
		int number = scan.nextInt();
		scan.nextLine(); // flush
		
		switch (number) {
			case 0:
				this.cursor = PathCursor.QUIT;
				break;
			case 1:
				this.cursor = PathCursor.BOARD_HOME;
				break;
			case 9:
				this.cursor = PathCursor.LOGOUT;
				break;
			default:
				break;
		}
		
		return;
	}
	
	private void homeUnauthorized() {
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 회원가입");
		System.out.println(" 2. 로그인");
		System.out.println(" 0. 종료");
		System.out.print("> ");
		
		int number = scan.nextInt();
		scan.nextLine(); // flush
		
		switch (number) {
			case 1:
				this.cursor = PathCursor.JOIN;
				break;
			case 2:
				this.cursor = PathCursor.LOGIN;
				break;
			case 0:
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
		System.out.print("> ");
		username = scan.nextLine();
		System.out.println("비밀번호를 작성하세요.");
		System.out.print("> ");
		password = scan.nextLine();
		System.out.println("닉네임을 작성하세요.");
		System.out.print("> ");
		nickname = scan.nextLine();
		
		JoinResultState joinState = memberService.join(username, password, nickname);
		if(joinState == JoinResultState.SUCCESS) {
			System.out.println("회원가입 성공");
			this.cursor = PathCursor.HOME;
		} else if(joinState == JoinResultState.USERNAME_DUPLICATED) {
			System.out.println("중복됩니다.");
			this.cursor = PathCursor.HOME; // 나중에 JOIN에서 입력 없이 복귀하는 기능이 있다면 그때 PathCursor.JOIN으로 유지
		} else if(joinState == JoinResultState.CONNECTION_ERROR) {
			System.out.println("연결문제");
			this.cursor = PathCursor.HOME;
		}
	}

	private void login() {
		String username;
		String password;
		
		System.out.println("===========================");
		System.out.println("=========== LOGIN ==========");
		System.out.println("아이디를 입력하세요.");
		System.out.print("> ");
		username = scan.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		System.out.print("> ");
		password = scan.nextLine();
		
		LoginResponseDto result = memberService.login(username, password);
		MemberVo member = result.getMember();
		LoginResultState state = result.getState();
		
		if (state == LoginResultState.SUCCESS) {
			System.out.println("로그인 성공");
			this.loginMember = member;
			this.cursor = PathCursor.HOME;
		} else if (state == LoginResultState.FAIL) {
			System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			this.cursor = PathCursor.HOME; // 나중에 LOGIN에서 입력 없이 복귀하는 기능이 있다면 그때 PathCursor.LOGIN으로 유지
		} else if (state == LoginResultState.CONNECTION_ERROR) {
			System.out.println("서버 오류로 현재 로그인을 수행할 수 없습니다.");
			this.cursor = PathCursor.HOME;
		}

		return;
	}
	
	private void logout() {
		loginMember = null;
		System.out.println("로그아웃 되었습니다.");
		this.cursor = PathCursor.HOME;
	}
	
	private void boardHome() {
		int sel;
		System.out.println("===========================");
		System.out.println("=========== BOARD ==========");
		System.out.println(" 1. 글쓰기");
		System.out.println(" 0. 뒤로가기");
		System.out.print("> ");
		sel = scan.nextInt();
		scan.nextLine();
		
		switch(sel) {
			case 1:
				this.cursor = PathCursor.BOARD_CREATE;
				break;
			case 0:
				this.cursor = PathCursor.HOME;
				break;
		}
	}
	
	private void boardAddForm() {
		String title;
		String content;
		String author = loginMember.getNickname();
		Long authorId = loginMember.getId(); // 원래는 UI단 작업이 아니라 DB에서 조회해서. <- 지금은 세션 기능이랑 같으니까 신뢰하고 씀.
		
		System.out.println("===========================");
		System.out.println("========= Add Form ========");
		System.out.print("제목: ");
		title = scan.nextLine();		
		System.out.println("내용: ");
		content = scan.nextLine();
		
		BoardAddResult result = boardService.add(title, content, authorId, author);
		if(result == BoardAddResult.SUCCESS) {
			this.cursor = PathCursor.BOARD_HOME;
		}
		return;
	}
}
