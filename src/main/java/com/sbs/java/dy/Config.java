package com.sbs.java.dy;

public class Config {
	public static String getDBUrl() {
		return "jdbc:mysql://localhost:3306/dy?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
	}
	public static String getDBId() {
		return "root";
	}
	public static String getDBPw() {
		return "0910";
	}

	public static String getDBDriverClassName() {
		return "com.mysql.cj.jdbc.Driver";
	}
}