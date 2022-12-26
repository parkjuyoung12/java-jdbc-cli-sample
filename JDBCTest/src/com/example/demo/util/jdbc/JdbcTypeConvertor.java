package com.example.demo.util.jdbc;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

public interface JdbcTypeConvertor {
	OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp);
	Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime);
}
