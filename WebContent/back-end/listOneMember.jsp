<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  MemberVo memberVo = (MemberVo) request.getAttribute("memberVo"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

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
	</tr>
</table>

</body>
</html>