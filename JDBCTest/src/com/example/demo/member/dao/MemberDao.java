package com.example.demo.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.sql.Date;

import com.example.demo.member.dto.MemberDto.LoginRequestDto;
import com.example.demo.member.dto.MemberDto.LoginResponseDto;
import com.example.demo.member.domain.JoinResultState;
import com.example.demo.member.domain.LoginResultState;
import com.example.demo.member.dto.MemberVo;
import com.example.demo.util.jdbc.DefaultConnectionProvider;
import com.example.demo.util.jdbc.DefaultJdbcTypeConvertor;
import com.example.demo.util.jdbc.JdbcTypeConvertor;
import com.example.demo.util.jdbc.OracleConnectionProvider;

public class MemberDao {
	
	private final OracleConnectionProvider connectionProvider = new DefaultConnectionProvider();
	private final JdbcTypeConvertor typeConvertor = new DefaultJdbcTypeConvertor();

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
			e.printStackTrace();
			return JoinResultState.CONNECTION_ERROR;
		}
		
		return JoinResultState.SUCCESS;
	}
	
	public LoginResponseDto login(LoginRequestDto member) {
		ResultSet rs;
		LoginResponseDto result = null;
		
		try(Connection conn = connectionProvider.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT id, username, nickname, joindate FROM member WHERE username = ? AND password = ?");
			stmt.setString(1, member.getUsername());
			stmt.setString(2, member.getPassword());
		
			rs = stmt.executeQuery();
			// rs -> MemberVo
			
			boolean hasNext = rs.next();
			
			if(!hasNext) {
				result = new LoginResponseDto(null, LoginResultState.CONNECTION_ERROR);
				return new LoginResponseDto(null, LoginResultState.FAIL);
			}
			
			Long id = rs.getLong("id");
			String username = rs.getString("username");
			String nickname = rs.getString("nickname");
			Timestamp joindateTimestamp = rs.getTimestamp("joindate");
			OffsetDateTime joindate = typeConvertor.timestampToOffsetDateTime(joindateTimestamp);
			
			result = new LoginResponseDto(id, username, nickname, joindate, LoginResultState.SUCCESS);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = new LoginResponseDto(null, LoginResultState.CONNECTION_ERROR);
		}
		
		return result;
	}
	
}
