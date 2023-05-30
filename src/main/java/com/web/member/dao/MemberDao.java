package com.web.member.dao;

import static com.web.member.common.JDBCTemplete.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.web.member.dto.Member;

public class MemberDao {
	private final Properties sql=new Properties(); // static이나 final으로 선언해도 된다.
	{
		String path=MemberDao.class.getResource("/sql/member/member_sql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public int checkMember(String userId, String password, Connection conn) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql="SELECT * FROM MEMBER WHERE USERID=? AND PASSWORD=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member selectByUserIdAndPwd(String userId, String password, Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;// where절에 pk값이 들어가면 다중 값이 출력되지 않으므로 일치하는 값이 없으면 null이 출력된다.
		String sql=this.sql.getProperty("selectByUserIdAndPwd");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			// where절에 pk값이 들어가면 다중 값이 출력되지 않는다.
			if(rs.next()) {
				m=getMember(rs);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
		
	}
	
	public int checkMember(Connection conn,Member m) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=this.sql.getProperty("insertMember");
		// sql문에 enrolldate가 sysdate로 default값으로 지정되어있어서 sysdate 말고 default로 지정해도된다.
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
//			pstmt.setString(4, Character.toString(m.getGender()));
			pstmt.setString(4, String.valueOf(m.getGender()));
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, String.join(",",m.getHobby())); // 배열을 스트링으로 바꿔줌
			result=pstmt.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member idCheck(Connection conn, String id) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		Member m=null;
		String sql=this.sql.getProperty("idCheck");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	private Member getMember(ResultSet rs) throws SQLException {
			return Member.builder().userId(rs.getString("USERID")).password(rs.getString("PASSWORD")).userName(rs.getString("USERNAME"))
					.gender(rs.getString("GENDER").charAt(0)).age(rs.getInt("AGE")).email(rs.getString("EMAIL")).phone(rs.getString("PHONE"))
					.address(rs.getString("ADDRESS")).hobby(rs.getString("HOBBY").split(",")).enrollDate(rs.getDate("ENROLLDATE")).build();
	}
}
