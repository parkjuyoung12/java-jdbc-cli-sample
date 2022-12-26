package com.example.demo.board.service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

import com.example.demo.board.dao.BoardDao;
import com.example.demo.board.domain.BoardAddResult;
import com.example.demo.board.dto.BoardVo;
import com.example.demo.board.dto.BoardDto.BoardCreateRequest;

public class DefaultBoardService implements BoardService {
	
	private final BoardDao boardDao = new BoardDao();

	@Override
	public BoardAddResult add(String title, String content, Long authorId, String author) {
		BoardCreateRequest dto = new BoardCreateRequest(
				title,
				content,
				authorId,
				author,
				OffsetDateTime.now(ZoneId.of("Asia/Tokyo")));
		
		return boardDao.save(dto);
	}
	
	@Override
	public List<BoardVo> list() {
		List<BoardVo> list = boardDao.list();
		
		return list;
	}
}
