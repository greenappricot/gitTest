package com.jdbc.model;

import java.sql.Date;
import java.util.Objects;

public class BoardComment {
	private int commentN;
	private String boardContent;
	private String commentWriter; 
	// -> writer : 사용자 -> 즉 회원ㅇㅣ므로 private Member commentWriter; 로 선언해야한다.
	private Date commentDate;
	
//	private BoardDto b; -> 양방향으로 검색하려면 설정해야한다. 
	// 원래 boardComment 테이블에는 참조값이 있지만 java 개념상 FK값이 없다. 
	
	public BoardComment() {
		// TODO Auto-generated constructor stub
	
	}

public BoardComment(int commentN, String boardContent, String commentWriter, Date commentDate) {
	super();
	this.commentN = commentN;
	this.boardContent = boardContent;
	this.commentWriter = commentWriter;
	this.commentDate = commentDate;
}

public int getCommentN() {
	return commentN;
}

public void setCommentN(int commentN) {
	this.commentN = commentN;
}

public String getBoardContent() {
	return boardContent;
}

public void setBoardContent(String boardContent) {
	this.boardContent = boardContent;
}

public String getCommentWriter() {
	return commentWriter;
}

public void setCommentWriter(String commentWriter) {
	this.commentWriter = commentWriter;
}

public Date getCommentDate() {
	return commentDate;
}

public void setCommentDate(Date commentDate) {
	this.commentDate = commentDate;
}


@Override
public String toString() {
	return "BoardComment [commentN=" + commentN + ", boardContent=" + boardContent + ", commentWriter=" + commentWriter
			+ ", commentDate=" + commentDate + "]";
}

@Override
public int hashCode() {
	return Objects.hash(boardContent, commentDate, commentN, commentWriter);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	BoardComment other = (BoardComment) obj;
	return Objects.equals(boardContent, other.boardContent) && Objects.equals(commentDate, other.commentDate)
			&& commentN == other.commentN && Objects.equals(commentWriter, other.commentWriter);
}
	
	
	
}
