package com.example.demo.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.sql.Date;

import com.example.demo.member.dto.MemberVo;
import com.example.demo.util.jdbc.DefaultConnectionProvider;
import com.example.demo.util.jdbc.OracleConnectionProvider;

public class MemberDao {
	
	private final OracleConnectionProvider connectionProvider = new DefaultConnectionProvider();

	public JoinResultState join(MemberVo member) {
		int result = 0;
		// offsetDateTime to LocalDate

		
		try (Connection conn = connectionProvider.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO member(id, username, password, nickname, joindate) "
					+ "values(seq_member.NEXTVAL, ?, ?, ?, ?)");
			stmt.setString(1, member.getUsername());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getNickname());
			stmt.setTimestamp(4, Timestamp.from(member.getJoindate().toInstant()));
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			return JoinResultState.USERNAME_DUPLICATED;
		} catch (SQLException e) {
			return JoinResultState.CONNECTION_ERROR;
		}
		
		return JoinResultState.SUCCESS;
	}
	
}
