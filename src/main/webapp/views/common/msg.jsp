<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String location=(String)request.getAttribute("loc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>System Message</title>
</head>
<body>
	<!-- js로 alert창 출력하기 -->
	<script>
		alert("<%=msg%>");
		location.replace("<%=request.getContextPath()%>/<%=location%>");
	</script>
	
</body>
</html>