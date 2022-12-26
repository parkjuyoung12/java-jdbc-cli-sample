package com.example.demo.util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface OracleConnectionProvider {
	static final String DRIVER_CLASSNAME = "oracle.jdbc.driver.OracleDriver";
	static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:letsdev";
	static final String USER_NAME = "test";
	static final String PASSWORD = "1234";
	
	Connection getConnection() throws SQLException;
}
