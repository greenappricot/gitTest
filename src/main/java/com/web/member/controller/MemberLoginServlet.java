package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.dto.Member;
import com.web.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트가 보낸 데이터를 가져옴
		//  userId, password
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		
		
		
		// 2. DB접속해서 id, password 일치하는 회원 있는지 확인
		Member loginMember=new MemberService().selectByUserIdAndPwd(userId, password);
		
		// 아이디 저장 로직 처리
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		// checkbox에서 value 안 적으면 check(o) -> on / check(x)-> null 값이 value로 넘어옴
		if(saveId!=null) {
			Cookie c=new Cookie("saveId",userId);
			c.setMaxAge(60*60*24*7);
			response.addCookie(c);
		}else {
			// 아이디 저장 체크 안 하고 넘겼을 때 로그아웃 하면 아이디저장 안되어있는 분기처리하기
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		
		// loginMember null 기준으로 로그인처리 여부 결정할 수 있음
		if(loginMember!=null) {
			// 로그인 성공 => 인증받음
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", loginMember);
			response.sendRedirect(request.getContextPath());
		}else {
			// 로그인 실패 => 인증 실패 => 실패 메세지 출력
			// 메세지 출력되는 페이지 만들어서 반환 후 다시 메인으로 이동하기
			request.setAttribute("msg", "아이디, 패스워드가 일치하지 않습니다 :(");
			request.setAttribute("loc", "/"); // "/" = main으로 이동하겠다
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		

		
		
//		int result=new MemberService().checkMember(userId, password);
//		if(result==1){
//			System.out.println("로그인 성공");
//		}else {
//			System.out.println("로그인 실패");
//		}
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
