package com.example.demo.board.dto;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BoardVo {
	private Long id;
	private String title;
	private String content;
	private String author;
	private Long authorId;
	private OffsetDateTime createdAt;
	
	public BoardVo(Long id, String title, String author, OffsetDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.createdAt = createdAt;
	}

	public BoardVo(Long id, String title, String content, String author, Long authorId, OffsetDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.authorId = authorId;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	public String getFormattedCreatedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		String formattedCreatedAt = formatter.format(this.createdAt);
		
		return formattedCreatedAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return String.format("BoardVO{id=%d, title=%s, author=%s, content=%s, cratedAt=%s}", 
				this.id, 
				this.title, 
				this.author, 
				this.content, 
				this.getFormattedCreatedAt());
	}
}
