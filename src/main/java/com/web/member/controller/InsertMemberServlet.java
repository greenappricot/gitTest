package com.web.member.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.dto.Member;
import com.web.service.MemberService;

@WebServlet("/member/insertmember.do")
public class InsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 입력한 데이터 가져오기
//		request.setCharacterEncoding("utf-8"); // 모든 서블릿에 적용하기 위해 filter에 패턴 적용한다.
		String userId=request.getParameter("userId"); 
		String password=request.getParameter("password"); 
		String userName=request.getParameter("userName"); 
		int age=Integer.parseInt(request.getParameter("age")); 
		String email=request.getParameter("email");
		String phone=request.getParameter("phone"); 
		String address=request.getParameter("address"); 
		String gender=request.getParameter("gender");
		String[] hobby=request.getParameterValues("hobby");
		System.out.println(userId+" "+password+" "+userName+" "+age+" "+email+" "+phone+" "+address+" "+gender+" "+Arrays.toString(hobby));
		
		// 데이터 저장 -> sql문 insert
		Member m=Member.builder().userId(userId).password(password).userName(userName).gender(gender.charAt(0)).
				age(age).email(email).phone(phone).hobby(hobby).build();
		int result= new MemberService().insertMember(m);
		String msg="", loc="";
		if(result>0) {
			// 입력 성공
			msg="회원가입 완료 :)";
			loc="/";
//			HttpSession member=request.getSession();
//			member.setAttribute("member", m);			
		}else {
			// 입력 실패
			// common의 msg.jsp로 이동
			msg="회원가입 실패 :ㅣ\n 다시 시도하세요!";
			loc="/member/enrollMember.do";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
