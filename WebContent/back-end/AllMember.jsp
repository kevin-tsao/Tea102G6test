<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	MemberService MemberSvc = new MemberService();
    List<MemberVo> list = MemberSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="MembertSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
<title>�Ҧ����u��� - listAllMember.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��|����� - listAllMember.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���b��</th>
		<th>�|���K�X</th>
		<th>�|���m�W</th>
		<th>�|���ʺ�</th>
		<th>�ʧO</th>
		<th>�q��</th>
		<th>�a�}</th>
		<th>�ͤ�</th>
		<th>�|���v��</th>
		<th>���H�s��</th>
		<th>�[�J���</th>	
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memberVo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memberVo.memberId}</td>
			<td>${memberVo.memberAccount}</td>
			<td>${memberVo.memberPassword}</td>
			<td>${memberVo.memberName}</td>
			<td>${memberVo.memberNickname}</td>
			<td>${memberVo.memberGender}</td>
			<td>${memberVo.memberPhone}</td>
			<td>${memberVo.memberAddress}</td>
			<td>${memberVo.memberBirth}</td>
			<td>${memberVo.memberMsgAuth}</td>
			<td>${memberVo.bandId}</td>
			<td>${memberVo.addTime}</td>
 			<%--<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%--                     <c:if test="${empVO.deptno==deptVO.deptno}"> --%>
<%-- 	                    ${deptVO.deptno}�i${deptVO.dname} - ${deptVO.loc}�j --%>
<%--                     </c:if> --%>
<%-- 					${deptSvc.getOneDept(empVO.deptno).dname} --%>
<%--                 </c:forEach> --%>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Login" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="memberId"  value="${memberVo.memberId}">
			     <input type="hidden" name="action"	value="updateMember"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Login" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="memberId"  value="${memberVo.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>