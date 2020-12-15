<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*" %>
<%MemberVo memberVo =(MemberVo)session.getAttribute("memberVo");%>
<%Base64.Encoder be= Base64.getEncoder();
	String photo = be.encodeToString(memberVo.getMemberPhoto());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>

    .borderbox{
        border:solid 2px black;
        height: 800px;
        width: 600px;
        margin: auto;
        position: relative;
    }
    .title{
     	position: absolute;
        margin: 0px 100px 30px;
        position: relative;
        font-size: 32px;
    }
    .member{
        position: absolute;
        margin-left: 40px;
        list-style: none;
        font-size: 18px;
        font-weight: bold;
        color : gray;
    }
    .memberac{
    	outline:none;
    	position: absolute;
    	margin:0px 90px;
    	font-size: 18px;
    	font-weight: bold;
    }
       .borderbox2{
        margin:30px 0px 0px 440px;
        position: absolute;
        border-radius: 50%;
        height: 120px;
        width: 120px;
        border:solid 2px black;
        overflow: hidden;
    }
    #pic{
        position: absolute;
        margin: 160px 0px 0px 465px;
        width:70px;
    }
    
    </style>
</head>
<body>
<form action="<%= request.getContextPath() %>/Login" method="POST"  id="upload" enctype="multipart/form-data">
    <div class="borderbox">
    	<div class="borderbox2"><img src ="<%="data:image/png;base64,"+photo%>"></div>
    	
    	<input type="file" id="pic" name="pic" >
        <div class="title"><h3>�ӤH���</h3></div>
        <ul class="member">
            	<li><p>�m�W<input type="text" class="memberac" id="memberName" value="${memberVo.memberName}" ><li>
            	<li><p>�p�W<input type="text" class="memberac" id="memberNickname" value="${memberVo.memberNickname}" ><li>
            	<li><p>�ʧO<input type="text" class="memberac" id="memberGender" value="${memberVo.memberGender}" ><li>
            	<li><p>�q��<input type="text" class="memberac" id="memberPhone" value="${memberVo.memberPhone}" ><li>
            	<li><p>�a�}<input type="text" class="memberac" id="memberAddress" value="${memberVo.memberAddress}" ><li>
<%--             	<li><p>�ͤ�<input type="date" class="memberac" id="memberBirth" value="${memberVo.memberBirth}" ><li>  --%>
            	<li><p>�H�Υd���X<input type="text" class="memberac" id="memberCardNumber" value="${memberVo.memberCardNumber}" ><li>
            	<li><p>�H�Υd����~��<input type="text" class="memberac" id="memberCardExpyear" value="${memberVo.memberCardExpyear}" ><li>
            	<li><p>�H�Υd������<input type="text" class="memberac" id="memberCardExpmonth" value="${memberVo.memberCardExpmonth}" ><li>       
        </ul>
        <input type="hidden" name="action" value="updatePic">
    </div>
     <input type="button" id="update"name="update" value="update">
    </form>
    <script src="/TEA102G6/jquery/jquery-3.5.1.min.js"></script>
                <script>
                    let pic = document.getElementById("pic");
                    pic.addEventListener("change", function () {
                        let piczone = document.getElementsByClassName("borderbox2")[0];
                        piczone.innerHTML = "";
                        var reader = new FileReader();
                        reader.readAsDataURL(this.files[0]);
                        reader.addEventListener("load", function (e) {
                            let base64 = e.target.result;
                            let photo = document.createElement("Img");
                            photo.setAttribute("src", base64);
                            piczone.append(photo);
                            let obj = new FormData($("#upload")[0]);
                         	console.log(obj);
                            $.ajax({
                				type : "POST",
                				url : "/TEA102G6/Login",
                				contentType: false,
                				processData : false,
                				cache : false,
                				data:obj,
                				success : function(result) {
                					alert("���\�ǰe");
                				},
                				error : function(err) {
                					alert("�t�ο��~");
                				}
                			});
                })

            })
           let update = document.getElementById("update");
        	update.addEventListener("click",function(){
           let memberName= $("#memberName").val();
           let memberNickname= $("#memberNickname").val()
           let memberGender= $("#memberGender").val()
           let memberPhone= $("#memberPhone").val()
           let memberAddress= $("#memberAddress").val()
           let memberCardNumber= $("#memberCardNumber").val()
           let memberCardExpyear= $("#memberCardExpyear").val()
           let memberCardExpmonth= $("#memberCardExpmonth").val()
          let obj ={
              	action:"updateac",
                memberName : memberName,
                memberNickname :memberNickname,
                memberGender :memberGender,
                memberPhone :memberPhone,
                memberAddress :memberAddress,
                memberCardNumber: memberCardNumber,
                memberCardExpyear:memberCardExpyear,
                memberCardExpmonth:memberCardExpmonth,
               }
           $.ajax({
                    type: "POST",
                    url: "/TEA102G6/Login",
                    data:obj,
                    success: function (result) {
                        alert("���\�ǰe111");
                    },
                    error: function (err) {
                        alert("�t�ο��~");
                    }
                });
        });
                </script>
</body>
</html>