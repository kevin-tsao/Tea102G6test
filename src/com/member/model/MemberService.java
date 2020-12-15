package com.member.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class MemberService {
	private MemberDao_interface memberDao;
	
	public MemberService() {
		memberDao = new MemberDaoJDBC();
	}
	public List<MemberVo> getAll(){
		return memberDao.getAll();
	}
	public MemberVo findByAccount(String memberAccount) {
		return memberDao.findByAccount(memberAccount);
	}
	public  MemberVo addMember(String memberAccount,String memberPassword,
			String memberGender,String memberPhone,String memberAddress,String memberName,String memberNickname,java.sql.Date memberBirth,
			Integer memberMsgAuth,String memberCardNumber,Integer memberCardExpyear,Integer memberCardExpmonth,Timestamp addTime,String bandId) {
				MemberVo memberVo = new MemberVo();
				memberVo.setMemberAccount(memberAccount);
				memberVo.setMemberPassword(memberPassword);
				memberVo.setMemberGender(memberGender);
				memberVo.setMemberPhone(memberPhone);
				memberVo.setMemberAddress(memberAddress);
				memberVo.setMemberName(memberName);
				memberVo.setMemberNickname(memberNickname);
				memberVo.setMemberBirth(memberBirth);
				memberVo.setMemberMsgAuth(memberMsgAuth);
				memberVo.setMemberCardNumber(memberCardNumber);
				memberVo.setMemberCardExpyear(memberCardExpyear);
				memberVo.setMemberCardExpmonth(memberCardExpmonth);
				memberVo.setAddTime(addTime);
				memberVo.setBandId(bandId);
				
				memberDao.insert(memberVo);
				
				
				return memberVo;
		
	}
	public MemberVo updateMember(String memberPhone,String memberAddress,String memberNickname,java.sql.Date memberBirth) {
		MemberVo memberVo = new MemberVo();
		memberVo.setMemberPhone(memberPhone);
		memberVo.setMemberAddress(memberAddress);
		memberVo.setMemberNickname(memberNickname);
		memberVo.setMemberBirth(memberBirth);
		memberDao.update(memberVo);
		return memberVo;
		
	}
	public void delet(String memberId) {
		memberDao.delete(memberId);
	}
	public MemberVo getOne(String memberId) {
		return memberDao.findByPrimaryKey(memberId);
	}
	public MemberVo login(String memberAccount, String memberPassword) {
		MemberVo memberVo = memberDao.findByAccount(memberAccount);
		if(memberVo!=null) {
			if(memberPassword!=null && memberPassword.length()!=0) {
				String pass = memberVo.getMemberPassword();
				if(pass.equals(memberPassword)) {
					return memberVo;
				}
			}
		}
		return null;
	}
	public MemberVo updatePhoto(MemberVo memberVo,byte[] pic) {
		memberDao.updatePic(memberVo,pic);
		return memberVo;
	}
	public MemberVo updateMemberinfo(String memberName,String memberNickname,String memberGender,
			String memberPhone,String memberAddress,String  memberCardNumber,Integer memberCardExpyear,Integer memberCardExpmonth,MemberVo memberVo) {
		
		memberVo.setMemberId(memberVo.getMemberId());
		memberVo.setMemberPhone(memberPhone);
		memberVo.setMemberAddress(memberAddress);
		memberVo.setMemberNickname(memberNickname);
		memberVo.setMemberCardExpmonth(memberCardExpmonth);
		memberVo.setMemberCardExpyear(memberCardExpyear);
		memberVo.setMemberCardNumber(memberCardNumber);
		memberVo.setMemberName(memberName);
		memberVo.setMemberGender(memberGender);
		memberDao.updateInfo(memberVo);
		return memberVo;
		
	}
	
}
