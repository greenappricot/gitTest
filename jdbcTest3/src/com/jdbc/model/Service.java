package com.jdbc.model;

import static com.jdbc.common.JdbcTemplete.close;
import static com.jdbc.common.JdbcTemplete.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.jdbc.common.JdbcTemplete;


public class Service {
	
	private Dao dao= new Dao();
	
	public List<BoardDto> selectBoardAll() {
		Connection conn=JdbcTemplete.getConnection();
		List<BoardDto> board= dao.selectBoardAll(conn);
		for(BoardDto b : board) {
			b.setComments(dao.selectBoardComment(conn, b.getBoardNo()));
		}
		close(conn);
		return board;
	}
	
	public List<BoardDto> searchBoard(Map param){
		Connection conn= getConnection();
		List<BoardDto> board= dao.searchBoard(conn, param);
		close(conn);
		return board;
	}
	
}
