package com.example.demo.board.service;

import java.util.List;

import com.example.demo.board.domain.BoardAddResult;
import com.example.demo.board.dto.BoardDto.BoardDetailResponse;
import com.example.demo.board.dto.BoardVo;

public interface BoardService {

	BoardAddResult add(String title, String content, Long authorId, String author);
	List<BoardVo> list(); 
	BoardDetailResponse detail(Long boardNum);
}
