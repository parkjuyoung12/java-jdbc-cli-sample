package com.example.demo.util.jdbc;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class DefaultJdbcTypeConvertor implements JdbcTypeConvertor {
	@Override
	public OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp) {
		Long epochMillis = timestamp.getTime();
		Instant instant = Instant.ofEpochMilli(epochMillis);
		OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(
				instant,
				ZoneId.of("Asia/Tokyo"));
		
		return offsetDateTime;
	}
}
