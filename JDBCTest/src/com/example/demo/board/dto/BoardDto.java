package com.example.demo.board.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.example.demo.board.domain.BoardDetailState;

public class BoardDto {

	public static final class BoardCreateRequest implements Serializable {
		private String title;
		private String content;
		private Long authorId;
		private String author;
		private OffsetDateTime createdAt;
		
		public BoardCreateRequest(String title, String content, Long authorId, String author, OffsetDateTime createdAt) {
			super();
			this.title = title;
			this.content = content;
			this.authorId = authorId;
			this.author = author;
			this.createdAt = createdAt;
		}

		public String getTitle() {
			return title;
		}

		public String getContent() {
			return content;
		}

		public Long getAuthorId() {
			return authorId;
		}

		public String getAuthor() {
			return author;
		}

		public OffsetDateTime getCreatedAt() {
			return createdAt;
		}
	}
	
	public static final class BoardDetailResponse implements Serializable {
		
		private final BoardVo board;
		private final BoardDetailState state;
		
		public BoardDetailResponse(BoardVo board, BoardDetailState state) {
			super();
			this.board = board;
			this.state = state;
		}

		public BoardVo getBoard() {
			return board;
		}

		public BoardDetailState getState() {
			return state;
		}

		@Override
		public String toString() {
			return String.format("BoardDetailResponse {board=%s, state=%s}",
					this.board.toString(),
					this.state);
		}
	}
}
