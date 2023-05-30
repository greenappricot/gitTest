package com.web.service;

import static com.web.member.common.JDBCTemplete.*;
import static com.web.member.common.JDBCTemplete.getConnection;

import java.sql.Connection;

import com.web.member.dao.MemberDao;
import com.web.member.dto.Member;

public class MemberService {
	
	private MemberDao dao=new MemberDao();
	public int checkMember(String userId, String password) {
		Connection conn=getConnection();
		int result=dao.checkMember(userId, password, conn);
		close(conn);
		return result;
	}
	public Member selectByUserIdAndPwd(String userId, String password) {
		Connection conn=getConnection();
		Member m=dao.selectByUserIdAndPwd(userId, password, conn);
		close(conn);
		return m;
	}
	
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.checkMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Member idCheck(String id) {
		Connection conn=getConnection();
		Member m= dao.idCheck(conn, id);
		close(conn);
		return m;
	}
}
