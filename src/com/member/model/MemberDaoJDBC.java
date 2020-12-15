package com.member.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberDaoJDBC implements MemberDao_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G6";
	String passwd = "123456";
	private static final String INSERT_STMT="insert into member (member_Id,member_Account,member_Password,member_Gender,member_Phone,member_Address,member_Name,member_Nickname,member_Birth,member_Photo,member_Msg_Auth,member_Card_Number,member_Card_Expyear,member_Card_Expmonth,member_add_Time,band_Id) values('member'||LPAD(member_seq.NEXTVAL, 5, '0'),?,?,?,?,?,?,?,?,empty_blob(),1,?,?,?,?,?)";
	private static final String DELETE_BYPRIMARYKEY="delete from member where member_Id =?";
	private static final String UPDATE_STMT="update member set member_phone=?,member_address=?,member_nickname=?,member_birth=? where member_id = ?";
	private static final String FIND_BY_PRIMARY_KEY="select * from member where member_id = ?";
	private static final String GET_ALL="select * from member order by member_id";
	private static final String UPDATE_PASSWORD="update member set member_password=? where member_id=?";
	private static final String UPDATE_MEMBER_AUTH="update member set memeber_msg_auth=? where member_id=?";
	private static final String UPDATE_MEMBER_CARD="update member set member_Card_Number=?,member_Card_Expyear=?,member_Card_Expmonth=? where member_id=?";
	private static final String UPDATE_MEMBER_PHOTO="update member set member_photo=? where member_id=?";
	private static final String FIND_BY_Account="select * from member where member_Account = ?";
	private static final String UPDATE_MEMBER_INFO="update member set member_Gender=?,member_phone=?,member_address=?,member_Name=?,member_nickname=?,Member_Card_Number=?,member_Card_Expyear=?,member_Card_Expmonth=? where member_id = ?";
	private static final String UPDATE_MEMBER_INFO_BACK="update member set member_Name=?, member_Nickname=?, member_Gender=?, member_Phone=?, member_Address=?  where member_id = ?";
	@Override
	public void insert(MemberVo memberVo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, memberVo.getMemberAccount());
			psmt.setString(2, memberVo.getMemberPassword());
			psmt.setString(3, memberVo.getMemberGender());
			psmt.setString(4, memberVo.getMemberPhone());
			psmt.setString(5, memberVo.getMemberAddress());
			psmt.setString(6, memberVo.getMemberName());
			psmt.setString(7, memberVo.getMemberNickname());
			psmt.setDate(8, memberVo.getMemberBirth());
			psmt.setString(9, memberVo.getMemberCardNumber());
			psmt.setInt(10, memberVo.getMemberCardExpyear());
			psmt.setInt(11, memberVo.getMemberCardExpmonth());
			psmt.setTimestamp(12,memberVo.getAddTime());
			psmt.setString(13, memberVo.getBandId());
			psmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(MemberVo memberVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(UPDATE_STMT);
			psmt.setString(1, memberVo.getMemberPhone());
			psmt.setString(2,memberVo.getMemberAddress());
			psmt.setString(3,memberVo.getMemberNickname());
			psmt.setDate(4, memberVo.getMemberBirth());
			psmt.setString(5, memberVo.getMemberId());
			psmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String memberId) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(DELETE_BYPRIMARYKEY);
			psmt.setString(1, memberId);
			psmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}

	@Override
	public MemberVo findByPrimaryKey(String memberId) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MemberVo memberVo = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(FIND_BY_PRIMARY_KEY);
			psmt.setString(1, memberId);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				memberVo = new MemberVo();
				memberVo.setMemberId(rs.getString("Member_Id"));
				memberVo.setMemberAccount(rs.getString("Member_Account"));
				memberVo.setMemberPassword(rs.getString("Member_Password"));
				memberVo.setMemberGender(rs.getString("Member_Gender"));
				memberVo.setMemberPhone(rs.getString("Member_Phone"));
				memberVo.setMemberAddress(rs.getString("Member_Address"));
				memberVo.setMemberName(rs.getString("Member_Name"));
				memberVo.setMemberNickname(rs.getString("Member_Nickname"));
				memberVo.setMemberPhoto(rs.getBytes("Member_Photo"));
				memberVo.setMemberMsgAuth(rs.getInt("Member_Msg_Auth"));
				memberVo.setMemberCardNumber(rs.getString("Member_Card_Number"));
				memberVo.setMemberCardExpyear(rs.getInt("Member_Card_Expyear"));
				memberVo.setMemberCardExpmonth(rs.getInt("Member_Card_Expmonth"));
				memberVo.setAddTime(rs.getTimestamp("Member_add_time"));
				memberVo.setBandId(rs.getString("band_id"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return memberVo;
		}

	@Override
	public List<MemberVo> getAll() {
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MemberVo memberVo = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(GET_ALL);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				memberVo = new MemberVo();
				memberVo.setMemberId(rs.getString("Member_Id"));
				memberVo.setMemberAccount(rs.getString("Member_Account"));
				memberVo.setMemberPassword(rs.getString("Member_Password"));
				memberVo.setMemberGender(rs.getString("Member_Gender"));
				memberVo.setMemberPhone(rs.getString("Member_Phone"));
				memberVo.setMemberAddress(rs.getString("Member_Address"));
				memberVo.setMemberName(rs.getString("Member_Name"));
				memberVo.setMemberNickname(rs.getString("Member_Nickname"));
				memberVo.setMemberPhoto(rs.getBytes("Member_Photo"));
				memberVo.setMemberMsgAuth(rs.getInt("Member_Msg_Auth"));
				memberVo.setMemberCardNumber(rs.getString("Member_Card_Number"));
				memberVo.setMemberCardExpyear(rs.getInt("Member_Card_Expyear"));
				memberVo.setMemberCardExpmonth(rs.getInt("Member_Card_Expmonth"));
				memberVo.setAddTime(rs.getTimestamp("Member_add_time"));
				memberVo.setBandId(rs.getString("band_id"));
				list.add(memberVo);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return list;
		}
	public void updatePassword(String memberID,String memberPassword) {  
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(UPDATE_PASSWORD);
			psmt.setString(2,memberID);
			psmt.setString(1, memberPassword);
			psmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public MemberVo findByAccount(String memberAccount) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		MemberVo memberVo = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(FIND_BY_Account);
			psmt.setString(1, memberAccount);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				memberVo = new MemberVo();
				memberVo.setMemberId(rs.getString("Member_Id"));
				memberVo.setMemberAccount(rs.getString("Member_Account"));
				memberVo.setMemberPassword(rs.getString("Member_Password"));
				memberVo.setMemberGender(rs.getString("Member_Gender"));
				memberVo.setMemberPhone(rs.getString("Member_Phone"));
				memberVo.setMemberAddress(rs.getString("Member_Address"));
				memberVo.setMemberName(rs.getString("Member_Name"));
				memberVo.setMemberNickname(rs.getString("Member_Nickname"));
				memberVo.setMemberPhoto(rs.getBytes("Member_Photo"));
				memberVo.setMemberMsgAuth(rs.getInt("Member_Msg_Auth"));
				memberVo.setMemberCardNumber(rs.getString("Member_Card_Number"));
				memberVo.setMemberCardExpyear(rs.getInt("Member_Card_Expyear"));
				memberVo.setMemberCardExpmonth(rs.getInt("Member_Card_Expmonth"));
				memberVo.setAddTime(rs.getTimestamp("Member_add_time"));
				memberVo.setBandId(rs.getString("band_id"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return memberVo;
		}

	@Override
	public void updatePic(MemberVo memberVo,byte[] pic) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(UPDATE_MEMBER_PHOTO);
			psmt.setBytes(1, pic);
			psmt.setString(2,memberVo.getMemberId());
			psmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void updateInfo(MemberVo memberVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(UPDATE_MEMBER_INFO);
			psmt.setString(2, memberVo.getMemberPhone());
			psmt.setString(3,memberVo.getMemberAddress());
			psmt.setString(5,memberVo.getMemberNickname());
			psmt.setString(4,memberVo.getMemberName());
			psmt.setString(1,memberVo.getMemberGender());
			psmt.setString(6, memberVo.getMemberCardNumber());
			psmt.setInt(7, memberVo.getMemberCardExpyear());
			psmt.setInt(8, memberVo.getMemberCardExpmonth());
			psmt.setString(9, memberVo.getMemberId());
			psmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void uddateInfoBack(MemberVo memberVo) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			psmt = con.prepareStatement(UPDATE_MEMBER_INFO_BACK);
			psmt.setString(4, memberVo.getMemberPhone());
			psmt.setString(5,memberVo.getMemberAddress());
			psmt.setString(2,memberVo.getMemberNickname());
			psmt.setString(1,memberVo.getMemberName());
			psmt.setString(3,memberVo.getMemberGender());
			psmt.setString(6, memberVo.getMemberId());
			psmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
		
	}

