package com.example.demo.board.service;

import com.example.demo.board.domain.BoardAddResult;

public interface BoardService {

	BoardAddResult add(String title, String content, Long authorId, String author);
	
}
