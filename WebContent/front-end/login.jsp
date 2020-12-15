<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
input {
	width: 300px;
	height: 30px;
}

.bigContainer tr {
	height: 35px;
}

#btnLogin {
	width: 300px;
	height: 30px;
}
</style>
</head>
<body>
	<div class="bigContainer">
		<div id="login-page">
			<form action="<%=request.getContextPath()%>/Login" method="get">
				會員帳號<input type="text" name="memberAccount" value="">
				 <span class="error">${errors.username}</span>
				  <br> 會員密碼<input type="text" name="memberPassword" value="">
				   <spanclass="error">${errors.password}</span> 
				   <br> 
				   <input type="submit" name="log" value="登入"> 
				   <input type="hidden" name="action"value="login">
				<jsp:useBean id="memberVo" scope="session"
					class="com.member.model.MemberVo" />

	

			</form>
		</div>
	</div>

</body>
</html>