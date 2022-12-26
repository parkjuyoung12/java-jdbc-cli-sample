package com.example.demo.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;

import com.example.demo.board.domain.BoardAddResult;
import com.example.demo.board.dto.BoardDto.BoardCreateRequest;
import com.example.demo.member.domain.JoinResultState;
import com.example.demo.util.jdbc.DefaultConnectionProvider;
import com.example.demo.util.jdbc.DefaultJdbcTypeConvertor;
import com.example.demo.util.jdbc.JdbcTypeConvertor;
import com.example.demo.util.jdbc.OracleConnectionProvider;

public class BoardDao {

	private final OracleConnectionProvider connectionProvider = new DefaultConnectionProvider();
	private final JdbcTypeConvertor typeConvertor = new DefaultJdbcTypeConvertor();

	public BoardAddResult save(BoardCreateRequest dto) {
		int result = 0;
		// offsetDateTime to LocalDate

		
		try (Connection conn = connectionProvider.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO board(id, title, content, author_id, author_name, created_at) "
					+ "values(seq_member.NEXTVAL, ?, ?, ?, ?, ?)");
			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getContent());
			stmt.setLong(3, dto.getAuthorId());
			stmt.setString(4, dto.getAuthor());
			Timestamp timestamp = typeConvertor.offsetDateTimeToTimestamp(dto.getCreatedAt());
			stmt.setTimestamp(5, timestamp);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return BoardAddResult.CONNECTION_ERROR;
		}
		
		return BoardAddResult.SUCCESS;
	}

	
	
}
