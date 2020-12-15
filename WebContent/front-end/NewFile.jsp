<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<% MemberVo memberVo = (MemberVo)request.getAttribute("memberVo");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="/TEA102G6/Login" method="POST">
        <ul style="list-style-type:none">
            <li>會員帳號<input type="text" name="memberAccount" value="<%= (memberVo==null)? "" : memberVo.getMemberAccount()%>">
            <span class="error">${errors.account}</span>
            </li>
            <li>會員密碼<input type="password" name="memberPassword" value="<%= (memberVo==null)? "" : memberVo.getMemberPassword()%>">
            </li>
            <li>性別<input type="radio" value="M" name="memberGender" <%= ((memberVo==null)||(memberVo.getMemberGender().equals("M")))? "checked" : "" %>>男
                <input type="radio" value="F" name="memberGender" <%= ((((memberVo!=null)&&(memberVo.getMemberGender().equals("F")))))? "checked" : "" %>>女</li>
            <li>手機號碼<input type="text" name="memberPhone" value="<%= (memberVo==null)? "" : memberVo.getMemberPhone()%>"><span class="error">${errors.phone}</span></li>
            <li>地址<input type="text" name="memberAddress" value="<%= (memberVo==null)? "" : memberVo.getMemberAddress()%>"></li>
            <li>會員姓名<input type="text" name="memberName" value="<%= (memberVo==null)? "" : memberVo.getMemberName()%>"></li>
            <li>會員暱稱<input type="text" name="memberNickname" value="<%= (memberVo==null)? "" : memberVo.getMemberAddress()%>"></li>
            <li>生日<input type="date" name="memberBirth" value="<%= (memberVo==null)? "" : memberVo.getMemberNickname()%>"></li>
            <li>信用卡號<input type="text" name="memberCardNumber" value="<%= (memberVo==null)? "" : memberVo.getMemberCardNumber()%>"></li>
            <li>信用卡到期年<input type="text" name="memberCardExpyear" value="<%= (memberVo==null)? "" : memberVo.getMemberCardExpyear()%>"></li>
            <li>信用卡到期月<input type="text" name="memberCardExpmonth" value="<%= (memberVo==null)? "" : memberVo.getMemberCardExpmonth()%>"></li>
            <li><input type="submit" value="註冊"></li>

        </ul>
        	<input type="hidden" name="action" value="registered">
    </form>

</body>
</html>