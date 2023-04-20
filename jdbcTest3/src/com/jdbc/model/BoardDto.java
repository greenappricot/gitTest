package com.jdbc.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardDto {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Date boardDate;
	
	private List<BoardComment> comments= new ArrayList(); //게시글 하나당 여러개의 댓글이 나올 수 있기 때문에 list로 데이터값을 받아서 사용해야한다.
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int boardNo, String boardTitle, String boardContent, String boardWriter, Date boardDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardDate = boardDate;
	}
	
	public List<BoardComment> getComments(){
		return comments;
	}
	
	public void setComments(List<BoardComment> comments) {
		this.comments=comments;
	}
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	@Override
	public String toString() {
		return "Dto [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardDate=" + boardDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(boardContent, boardDate, boardNo, boardTitle, boardWriter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardDto other = (BoardDto) obj;
		return Objects.equals(boardContent, other.boardContent) && Objects.equals(boardDate, other.boardDate)
				&& Objects.equals(boardNo, other.boardNo) && Objects.equals(boardTitle, other.boardTitle)
				&& Objects.equals(boardWriter, other.boardWriter);
	}
	
	
	
}
