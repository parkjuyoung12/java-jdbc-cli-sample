package com.example.demo.board.dto;

import java.time.OffsetDateTime;

public class BoardDto {

	public static final class BoardCreateRequest {
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
}
