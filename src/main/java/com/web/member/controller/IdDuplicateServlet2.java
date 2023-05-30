package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.dto.Member;
import com.web.service.MemberService;

/**
 * Servlet implementation class IdDuplicateServlet2
 */
@WebServlet("/member/idDuplicate.do")
public class IdDuplicateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdDuplicateServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 전송한 값이 DB(member테이블)에 있는지 확인하기
		String userId=request.getParameter("userId");
		
		Member m=new MemberService().idCheck(userId);
		request.setAttribute("result", m);		 
		
		request.getRequestDispatcher("/views/member/idDuplicate.jsp").forward(request, response);
		
		System.out.println(request.getAttribute("newId"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
