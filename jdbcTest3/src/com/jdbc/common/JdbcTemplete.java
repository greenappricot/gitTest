package com.jdbc.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTemplete {
	

	public static Connection getConnection() {
		
		Properties driver= new Properties(); 
		String path=JdbcTemplete.class.getResource("/driver.properties").getPath();
		// JdbcTemplete.class.getResource("/driver.properties") 값이 url로 반환되기 때문에 String 변수에 저장해서 사용한다.
		// 절대 경로로 경로를 지정했기 때문에 앞에 어떤 클래스로 접근해도 상관없다 -> 상대 경로로 변경하려면 해당 파일이 위치한 클래스로 접근해야한다.
		
		Connection conn=null;
		try(FileReader fr= new FileReader(path);) {
//			driver.load(new FileReader(path)); -> FileReader()를 닫아줘야하기 때문에 try() 안에 넣어서 처리해준다. try-end resource
			driver.load(fr);
			Class.forName(driver.getProperty("drivername"));
			conn= DriverManager.getConnection(driver.getProperty("url"), driver.getProperty("user"), driver.getProperty("pwd"));
			conn.setAutoCommit(false);
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
