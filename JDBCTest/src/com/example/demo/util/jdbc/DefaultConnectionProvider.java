package com.example.demo.util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DefaultConnectionProvider implements OracleConnectionProvider {
	
	// 이 클래스가 처음으로 사용될 때 실행되는 블럭(static 블럭)
	static {
		try {
			Class.forName(OracleConnectionProvider.DRIVER_CLASSNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				OracleConnectionProvider.JDBC_URL,
				OracleConnectionProvider.USER_NAME,
				OracleConnectionProvider.PASSWORD);
		
		return connection;
	}
}
