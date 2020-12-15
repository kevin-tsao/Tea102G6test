package com.member.controller;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVo;


@WebServlet("/Login")
@MultipartConfig
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String str = request.getParameter("action");
		if("login".equals(str)) {
			String memberAccount = request.getParameter("memberAccount");
			String memberPassword = request.getParameter("memberPassword");
			Map<String,String> errors = new HashMap<String,String>();
			request.setAttribute("errors", errors);
		if(memberAccount==null || memberAccount.length()==0) {
			errors.put("username", "請輸入帳號");
		}
		if(memberPassword==null || memberPassword.length()==0) {
			errors.put("password", "請輸入密碼");
		}
		
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/front-end/login.jsp").forward(request, response);
			return;
		}
		memberService = new MemberService();
		MemberVo memberVo = memberService.login(memberAccount, memberPassword);
		if(memberVo == null) {
			errors.put("password", "登入失敗 請檢查帳號密碼");
			request.getRequestDispatcher(
					"/front-end/login.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("memberVo", memberVo);
			
			String path = request.getContextPath();
			response.sendRedirect(path+"/front-end/member.jsp");
		}
		}
		if("registered".equals(str)) {
			String memberAccount = request.getParameter("memberAccount");
			Map<String,String> errors = new HashMap<String,String>();
			request.setAttribute("errors", errors);
			MemberService memberSvc = new MemberService();
			String accountReg = "^[a-z0-9A-Z]+@{1}";
			if(memberSvc.findByAccount(memberAccount)!=null) {
				errors.put("account", "帳號重複囉");
			}
			if(memberAccount==null || memberAccount.trim().isEmpty()) {
				errors.put("account2", "請輸入帳號");
			}
			if(memberAccount.matches(accountReg)) {
				errors.put("account2", "請輸入帳號");
			}
			String memberPassword = request.getParameter("memberPassword");
			if(memberPassword==null || memberPassword.trim().isEmpty()) {
				errors.put("password", "請輸入密碼");
			}
			String memberGender = request.getParameter("memberGender");
			if(memberGender==null|| memberGender.trim().isEmpty()) {
				errors.put("gender", "請勾選性別");
			}
			String memberPhone = request.getParameter("memberPhone");
			String phoneReg = "^0(9)[0-9]{8}$";
			if(!(memberPhone.matches(phoneReg))&& !(memberPhone.length()==10)) {
				errors.put("phone", "手機格式有誤");
			}
			String memberAddress = request.getParameter("memberAddress");
			String memberName = request.getParameter("memberName");
			if(memberName==null||memberName.trim().isEmpty()) {
				errors.put("name", "請輸入名字");
			}
			String memberNickname = request.getParameter("memberNickname");
			if(memberName==null||memberName.trim().isEmpty()) {
				memberNickname = null;
			}
			String memberBirth = request.getParameter("memberBirth");
			if(memberBirth==null) {
				errors.put("gender", "請選擇生日");
			}
			Integer memberMsgAuth = 1; //預設1
			String memberCardNumber = request.getParameter("memberCardNumber");
			Integer memberCardExpyear = Integer.valueOf(request.getParameter("memberCardExpyear"));
			Integer memberCardExpmonth = Integer.valueOf(request.getParameter("memberCardExpmonth"));
			java.util.Date date = new java.util.Date();
			Timestamp addTime = new Timestamp(date.getTime());
			String bandId = null;
//			MemberVo memberVo = new MemberVo();
			
			if(errors!=null && !errors.isEmpty()) {
				request.getRequestDispatcher(
						"/front-end/NewFile.jsp").forward(request, response);
				return;
			}
			memberSvc.addMember(memberAccount, memberPassword, memberGender, memberPhone, memberAddress, memberName, memberNickname, java.sql.Date.valueOf(memberBirth), memberMsgAuth, memberCardNumber, memberCardExpyear, memberCardExpmonth, addTime, bandId);
			System.out.println("註冊成功");
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/login.jsp"); // 新增成功後轉交listAllEmp.jsp
			successView.forward(request, response);
		}

		
		if("updatePic".equals(str)) {
			HttpSession session = request.getSession();
			MemberVo memberVo = (MemberVo)session.getAttribute("memberVo");
			Part part = request.getPart("pic");
			System.out.println(part);
			InputStream in = part.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();
			memberService = new MemberService();
			memberService.updatePhoto(memberVo,buf);
//			RequestDispatcher successView = request.getRequestDispatcher("/front-end/member.jsp");
//			successView.forward(request, response);
			
			}
		if("updateac".equals(str)) {
		
			String memberName = (String)request.getParameter("memberName");
			String memberNickname = (String)request.getParameter("memberNickname");
			String memberGender = (String)request.getParameter("memberGender");
			String memberPhone = (String)request.getParameter("memberPhone");
			String phoneReg = "^0(9)[0-9]{8}$";
			if(!(memberPhone.matches(phoneReg))&& !(memberPhone.length()==10)) {
				
			}
			String memberAddress = (String)request.getParameter("memberAddress");
			String memberCardNumber = (String)request.getParameter("memberCardNumber");
			Integer memberCardExpyear = Integer.valueOf(request.getParameter("memberCardExpyear"));
			Integer memberCardExpmonth = Integer.valueOf(request.getParameter("memberCardExpmonth"));
			HttpSession session = request.getSession();
			MemberVo memberVo = (MemberVo)session.getAttribute("memberVo");
			memberService = new MemberService();
			memberService.updateMemberinfo(memberName, memberNickname, memberGender, memberPhone, memberAddress, memberCardNumber, memberCardExpyear, memberCardExpmonth, memberVo);
			
           
			
		}
		if("addMember".equals(str)) {
			String memberAccount = request.getParameter("memberAccount");
			Map<String,String> errors = new HashMap<String,String>();
			request.setAttribute("errors", errors);
			MemberService memberSvc = new MemberService();
			String accountReg = "^[a-z0-9A-Z]+@{1}";
			if(memberSvc.findByAccount(memberAccount)!=null) {
				errors.put("account", "帳號重複囉");
			}
			if(memberAccount==null || memberAccount.trim().isEmpty()) {
				errors.put("account2", "請輸入帳號");
			}
			if(memberAccount.matches(accountReg)) {
				errors.put("account2", "帳號格式有誤");
			}
			String memberPassword = request.getParameter("memberPassword");
			if(memberPassword==null || memberPassword.trim().isEmpty()) {
				errors.put("password", "請輸入密碼");
			}
			String memberGender = request.getParameter("memberGender");
			if(memberGender==null|| memberGender.trim().isEmpty()) {
				errors.put("gender", "請勾選性別");
			}
			String memberPhone = request.getParameter("memberPhone");
			String phoneReg = "^0(9)[0-9]{8}$";
			if(!(memberPhone.matches(phoneReg))&& !(memberPhone.length()==10)) {
				errors.put("phone", "手機格式有誤");
			}
			String memberAddress = request.getParameter("memberAddress");
			if(memberAddress==null||memberAddress.trim().isEmpty()) {
				memberAddress = null;
			}
			String memberName = request.getParameter("memberName");
			if(memberName==null||memberName.trim().isEmpty()) {
				errors.put("name", "請輸入名字");
			}
			String memberNickname = request.getParameter("memberNickname");
			if(memberName==null||memberName.trim().isEmpty()) {
				memberNickname = null;
			}
			String memberBirth = request.getParameter("memberBirth");
			if(memberBirth==null) {
				errors.put("memberBirth", "請選擇生日");
			}
			Integer memberMsgAuth = 1; //預設1
			String memberCardNumber = null;
			Integer memberCardExpyear = 0;
			Integer memberCardExpmonth = 0;
			java.util.Date date = new java.util.Date();
			Timestamp addTime = new Timestamp(date.getTime());
			String bandId = null;
//			MemberVo memberVo = new MemberVo();
			if(errors!=null && !errors.isEmpty()) {
				request.getRequestDispatcher(
						"/back-end/addMember.jsp").forward(request, response);
				return;
			}
			memberSvc.addMember(memberAccount, memberPassword, memberGender, memberPhone, memberAddress, memberName, memberNickname, java.sql.Date.valueOf(memberBirth), memberMsgAuth, memberCardNumber, memberCardExpyear, memberCardExpmonth, addTime, bandId);
			System.out.println("註冊成功");
			RequestDispatcher successView = request.getRequestDispatcher("/back-end/AllMember.jsp"); // 新增成功後轉交listAllEmp.jsp
			successView.forward(request, response);
	}
		if("getone".equals(str)) {
			String memberId = (String) (request.getParameter("memberId"));
			MemberService memberSvc = new MemberService();
			MemberVo memberVo = memberSvc.getOne(memberId);
			request.setAttribute("memberVo", memberVo);
			String url = "/back-end/updateMember.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);		
		}
		if("updateMember".equals(str)) {
			Map<String,String> errors = new HashMap<String,String>();
			request.setAttribute("errors", errors);
			String memberId = (String)request.getParameter("memberId");
			String memberAccount =(String)request.getParameter("memberAccount");
			String memberPassword =(String)request.getParameter("memberPassword");
			String memberName = (String)request.getParameter("memberName");
			String memberNickname = (String)request.getParameter("memberNickname");
			String memberGender = (String)request.getParameter("memberGender");
			String memberPhone = (String)request.getParameter("memberPhone");
			String phoneReg = "^0(9)[0-9]{8}$";
//			if(!(memberPhone.matches(phoneReg))&& !(memberPhone.length()==10)) {
//				errors.put("phone", "手機格式有誤");
//			}
			String memberAddress = (String)request.getParameter("memberAddress");
			MemberVo memberVo = new MemberVo();
			memberVo.setMemberId(memberId);
			memberVo.setMemberAccount(memberAccount);
			memberVo.setMemberPassword(memberPassword);
			memberVo.setMemberAddress(memberAddress);
			memberVo.setMemberName(memberName);
			memberVo.setMemberNickname(memberNickname);
			memberVo.setMemberGender(memberGender);
			memberVo.setMemberPhone(memberPhone);
			System.out.println(memberAccount);
			System.out.println(memberPassword);
			System.out.println(memberAddress);
			System.out.println(memberName);
			System.out.println(memberNickname);
			System.out.println(memberGender);
			if(errors!=null && !errors.isEmpty()) {
			request.setAttribute("memberVo", memberVo);
			request.getRequestDispatcher("/back-end/updateMember.jsp").forward(request, response);
				return;
			}
			memberService = new MemberService();
			memberService.getOneForUpdate(memberName, memberNickname, memberGender, memberPhone, memberAddress, memberVo);
			String url = "/back-end/AllMember.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);		
		}
}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
}
