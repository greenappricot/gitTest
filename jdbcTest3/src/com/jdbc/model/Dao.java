package com.jdbc.model;


import static com.jdbc.common.JdbcTemplete.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jdbc.common.JdbcTemplete;

public class Dao {
	
	private Properties sql= new Properties();
	{
		String path=JdbcTemplete.class.getResource("/sql/board/board_sql.properties").getPath();
		try(FileReader fr= new FileReader(path);) {
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardDto> selectBoardAll(Connection conn) {
		List<BoardDto> board= new ArrayList<BoardDto>();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		// board_sql.properties 연결하기 
		String sql=this.sql.getProperty("selectBoardAll");
		
		try {
			
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
//			while(rs.next()) board.add(getBoard(rs));
			while(rs.next()) {
				BoardDto b= getBoard(rs);
				b.setComments(selectBoardComment(conn,b.getBoardNo()));
			} // -> service 클래스에서 for문으로 반복해서 가져오는 댓글 데이터를 Dao에서 바로 가져올 수 있다. 
			// -> SQL문 LEFT JOIN(null값 출력해야하니까)으로 parsing 해서 이전 값이랑 다음 값이랑 비교해서 중복값을 삭제할 수 있다 
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return board;
	}
	
//	public List<BoardDto> selectBoardComment(Connection conn, int num) {
//		List<BoardDto> board=new ArrayList<BoardDto>();
//		PreparedStatement pstmt= null;
//		ResultSet rs= null;
//		String sql=this.sql.getProperty("countComment");
//		
//		try {
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			rs= pstmt.executeQuery();
//			while(rs.next()) board.add(getBoard(rs));
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return board;
//	}
	
	public List<BoardComment> selectBoardComment(Connection conn, int boardNo) {
		List<BoardComment> comments= new ArrayList<BoardComment>();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String sql=this.sql.getProperty("selectBoardCommentByBoardNo");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				comments.add(getComment(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return comments;
	}
	
	public List<BoardDto> searchBoard(Connection conn, Map param){
		List<BoardDto> board= new ArrayList<BoardDto>();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		String sql= this.sql.getProperty("selectBoardByCol");
		sql=sql.replace("#COL", (String)param.get("col")); 
		// board_sql.properties 파일에서 가져올 sql 구문을 String으로 강제 형변환해서 "#COL"과 바꿔준다
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+(String)param.get("keyword")+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDto b= getBoard(rs);
				b.setComments(selectBoardComment(conn,b.getBoardNo()));
				board.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return board;
	}
	
	
	private BoardComment getComment(ResultSet rs) throws SQLException {
		BoardComment c= new BoardComment();
		c.setCommentN(rs.getInt("comment_no"));
		c.setBoardContent(rs.getString("comment_content"));
		c.setCommentWriter(rs.getString("comment_writer"));
		c.setCommentDate(rs.getDate("comment_date"));
		return c;
	}
	
	
	private BoardDto getBoard(ResultSet rs) throws SQLException {
		BoardDto b= new BoardDto();
		b.setBoardNo(rs.getInt("board_no"));
		b.setBoardTitle(rs.getString("board_title"));
		b.setBoardContent(rs.getString("board_content"));
		b.setBoardWriter(rs.getString("board_writer"));
		b.setBoardDate(rs.getDate("BOARD_DATE"));
		return b;
	}
	
}