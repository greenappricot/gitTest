<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.web.member.dto.Member" %>
<% 
	Member loginMember=(Member)session.getAttribute("loginMember"); 
	Cookie[] cookies= request.getCookies();
	String saveId=null;
	if(cookies!=null){
		for(Cookie c:cookies){
			if(c.getName().equals("saveId")){
				saveId=c.getValue();
				break;
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@100;200;300;400;500;600;700;800&display=swap" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
</head>
<title>Hello MVC</title>
<body>
	<div id="container">
		<header>
			<h1>Hello MVC</h1>
			<div class="login-container">
				<%if(loginMember==null) {%>
				<form action="<%=request.getContextPath() %>/login.do" id="loginFrm" method="post" onsubmit="return fn_validation();">
					<table>
						<tr>
							<td><input type="text" name="userId" id="userId" placeholder="아이디 입력" value="<%=saveId!=null?saveId:"" %>"></td>
						</tr>
						<tr>
							<td><input type="password" name="password" id="password" placeholder="패스워드 입력"></td>
							<td><input type="submit" value="로그인"></td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId"<%=saveId!=null?"checked":"" %>>아이디 저장
								<input type="button" value="회원가입" onclick="location.assign('<%=request.getContextPath()%>/enrollMember.do')">
							</td>
						</tr>
					</table>
				</form>
				<%}else{ %>
					<table id="logged-in">
						<tr>
							<td colspan="2">
								<%=loginMember.getUserName()%> 님 환영합니다 :)
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="내 정보 보기">
							</td>
							<td>
								<input type="button" value="로그아웃" onclick="location.replace('<%=request.getContextPath() %>/logout.do')">
							</td>
						</tr>
					</table>
				<%} %>
			</div>
			<nav>
				<ul class="main-nav">
					<li class="home">
						<a href="">Home</a>
					</li>
					<li id="notice">
						<a href="">Notice</a>
					</li>
					<li id="board">
						<a href="">Board</a>
					</li>
				</ul>
			</nav>
		</header>
		<script>
			/* const fn_validation=()=>{
				alert("제출");
				return false;
				// 누르면 알림 나오고 로그인되지 않는다.
			} */
			const fn_validation=()=>{
				const userId=$("#userId").val();
				if(userId.length<4){
					alert("아이디는 4글자 이상 입니다.");
					$("#userId").val("");
					$("#userId").focus();
					return false;
				};
				/* if($("#password").length<4){
					return false;
				} */
			}
		</script>