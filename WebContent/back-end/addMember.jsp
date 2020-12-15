<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVo memberVo = (MemberVo) request.getAttribute("memberVo");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��Ʒs�W - addmember.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>���u��Ʒs�W - addMember.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Login"
		" name="form1">
		<table>
				<tr>
				<th>�|���b��</th>
				<td><input type="TEXT" name="memberAccount" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberAccount()%>" /></td>
			</tr>
				<tr>
				<td>�|���K�X:</td>
				<td><input type="TEXT" name="memberPassword" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberPassword()%>" /></td>
			</tr>
			<tr>
				<td>�|���m�W:</td>
				<td><input type="TEXT" name="memberName" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberName()%>" /></td>
			</tr>
			<tr>
				<td>�|���ʺ�:</td>
				<td><input type="TEXT" name="memberNickname" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberNickname()%>" /></td>
			</tr>
			<tr>
				<td>�ʧO:</td>
				<td><input type="TEXT" name="memberGender" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberGender()%>" /></td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="memberPhone" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberPhone()%>" /></td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="memberAddress" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberAddress()%>" /></td>
			</tr>
			
			<tr>
				<td>�ͤ�:</td>
				<td><input name="memberBirth" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>�|���v��:</td>
				<td><input type="TEXT" name="memberMsgAuth" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getMemberMsgAuth()%>" /></td>
			</tr>
			<tr>
				<td>���H�s��:</td>
				<td><input type="TEXT" name="bandId" size="45"
					value="<%=(memberVo == null) ? "" : memberVo.getBandId()%>" /></td>
			</tr>
			
			

			<jsp:useBean id="MemberSvc" scope="page"
				class="com.member.model.MemberService" />

		</table>
		<br> <input type="hidden" name="action" value="addMember"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
	java.sql.Date memberBirth = null;
	try {
		memberBirth = memberVo.getMemberBirth();
	} catch (Exception e) {
		memberBirth = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=memberBirth%>', 
		   // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>