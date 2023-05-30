<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
	<section id=enroll-container>
        <h2>회원 가입 정보 입력</h2>
        <form action="<%=request.getContextPath()%>/member/insertmember.do" method="post" onsubmit="return fn_validateId();">
        <!-- return 선언 안 하면 페이지 넘어감 -->
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_" >
					<%-- <input type="button" value="중복확인" onclick="open('<%=request.getContextPath()%>/member/idduplicate.do','_blank','width=300, height=200')"> --%>
					<input type="button" value="중복확인" onclick="fn_duplicateId();">
				</td>
			</tr>
			<tr>
				<th></th>
				<td id="idLength"></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_"><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password_2" ><br>
				</td>
			</tr>
			<tr>
				<th></th>
				<td id="pwCheck"></td>
			</tr>  
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="userName" id="userName" ><br>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>	
				<input type="number" name="age" id="age"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" >
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F" >
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" onclick=""  >
		<input type="reset" value="취소">
        </form>
    </section>
    <script>
    	const fn_duplicateId=()=>{
    		const userId=$("#userId_").val();
    		if(userId.length>=4){
	    		open("<%=request.getContextPath()%>/member/idDuplicate.do?userId="+userId
	    				,'_blank','width=300, height=200');
    		}else{
    			alert("아이디는 4글자 이상 입력하세요")
    		}
    	}
    	function fn_validateId(){
    		const userId=$("#userId_").val();
    		if(userId.length<4){
    			alert("아이디는 4글자 이상 입력하세요");
    			/* $("#userId").val(""); 값 비우기 */
    			$("#userId").focus();
    			return false;
    		}
    	}
    	/* onsubmit 속성으로 함수 만들었기 때문에 가입 버튼 누른 이후에 함수 실행된다.*/
    	
    	/* 
    	내가 만든 로직
    	
    	$("#userId_").on("keyup",e=>{
    		if($("#userId_").val().length<4){
    			$("#idLength").text("아이디는 4글자 이상 입력하세요");    			
    		}else{
    			$("#idLength").text("");   
    		}
    	}) 
    	
    	const checkPw=$("#password_2");
    	$("#password_2").on("keyup",e=>{
    		if($("#password_").val()!=$("#password_2").val()){
    			$("#pwCheck").text("비밀번호가 일치하지 않습니다.");
    		}else{
    			$("#pwCheck").text("비밀번호가 일치합니다");
    		}
    	}); 
    	*/
    	$("#password_2").keyup(e=>{
    		const password=$("#password_").val();
    		const passwordCheck=$(e.target).val();
    		let color, msg;
    		if(password==passwordCheck){
    			color="green";
    			msg="비밀번호가 일치합니다.";
    		}else{
    			color="red";
    			msg="비밀번호가 일치하지 않습니다.";
    		}
    		/* console.log($(e.target).parents("tr").next().find("td")) */
    		/* $(e.target).parents("tr").next().find("td").last() (td로 2개를 만들었을 때는 이렇게 지정한다.)*/
    		const td=$(e.target).parents("tr").next().find("td");
    		td.html("");
    		$("<p>").css("color",color).text(msg).appendTo(td);
    	})
    	
    </script>
<%@ include file="/views/common/footer.jsp" %>