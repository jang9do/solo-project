package com.tj.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.dto.MemberDto;

//Memberに関するDao
public class MemberDao {
	public final static int OUTER=0; // 脱退した会員
	public final static int MASTER=1; // MASTER ADMIN
	public final static int SUBADMIN=2; // SUB ADMIN
	public final static int VIP=3; // VIP会員
	public final static int NOMAL=4; // 一般会員
	public final static int SUCCESS=1;
	public final static int FAIL=0;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static MemberDao instance = new MemberDao(); // シングルトーン
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection getConn() { // DBと連結するMethod
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public ArrayList<MemberDto> list(int startRow, int endRow) { // 全ての会員のListを貰うMethod
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, AL.* FROM (SELECT LNAME, A.* FROM MEMBER A, MLEVEL L WHERE A.LEVELN = L.LEVELN ORDER BY A.LEVELN, MJOIN) AL) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setmId(rs.getString("mId"));
				dto.setLevelN(rs.getInt("levelN"));
				dto.setlName(rs.getString("lName"));
				dto.setmPw(rs.getString("mPw"));
				dto.setmName(rs.getString("mName"));
				dto.setmBirth(rs.getTimestamp("mBirth"));
				dto.setmGender(rs.getString("mGender"));
				dto.setmPhone(rs.getString("mPhone"));
				dto.setmTel(rs.getString("mTel"));
				dto.setmAddress(rs.getString("mAddress"));
				dto.setmAdetail(rs.getString("mAdetail"));
				dto.setmJoin(rs.getTimestamp("mJoin"));
				if(rs.getInt("levelN")!=OUTER) {
					dtos.add(dto);
				}	
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return dtos;
	}
	
	public int join(MemberDto dto) { // 会員加入するMethod
		int result = 0;
		String sql = "INSERT INTO MEMBER VALUES(?, 4, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getmPw());
			pstmt.setString(3, dto.getmName());
			pstmt.setTimestamp(4, dto.getmBirth());
			pstmt.setString(5, dto.getmGender());
			pstmt.setString(6, dto.getmPhone());
			pstmt.setString(7, dto.getmTel());
			pstmt.setString(8, dto.getmAddress());
			pstmt.setString(9, dto.getmAdetail());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return result;
	}
	
	public ArrayList<MemberDto> search(String mId, String mName) { // ID、名前で会員を探すMethod
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		String sql = "SELECT LNAME, A.* FROM MEMBER A, MLEVEL L WHERE A.LEVELN = L.LEVELN AND MID LIKE '%'||?||'%' AND MNAME LIKE '%'||?||'%' ORDER BY A.LEVELN";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setmId(rs.getString("mId"));
				dto.setLevelN(rs.getInt("levelN"));
				dto.setlName(rs.getString("lName"));
				dto.setmPw(rs.getString("mPw"));
				dto.setmName(rs.getString("mName"));
				dto.setmBirth(rs.getTimestamp("mBirth"));
				dto.setmGender(rs.getString("mGender"));
				dto.setmPhone(rs.getString("mPhone"));
				dto.setmTel(rs.getString("mTel"));
				dto.setmAddress(rs.getString("mAddress"));
				dto.setmAdetail(rs.getString("mAdetail"));
				dto.setmJoin(rs.getTimestamp("mJoin"));
				if(rs.getInt("levelN")!=OUTER) {
					dtos.add(dto);
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return dtos;
	}
	
	public MemberDto login(String mId, String mPw) { // loginするMethod
		MemberDto dto = null;
		String sql = "SELECT LNAME, A.* FROM MEMBER A, MLEVEL L WHERE A.LEVELN = L.LEVELN AND MID=? AND MPW=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("levelN")!=OUTER) {
					dto = new MemberDto();
					dto.setmId(rs.getString("mId"));
					dto.setLevelN(rs.getInt("levelN"));
					dto.setlName(rs.getString("lName"));
					dto.setmPw(rs.getString("mPw"));
					dto.setmName(rs.getString("mName"));
					dto.setmBirth(rs.getTimestamp("mBirth"));
					dto.setmGender(rs.getString("mGender"));
					dto.setmPhone(rs.getString("mPhone"));
					dto.setmTel(rs.getString("mTel"));
					dto.setmAddress(rs.getString("mAddress"));
					dto.setmAdetail(rs.getString("mAdetail"));
					dto.setmJoin(rs.getTimestamp("mJoin"));
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return dto;
	}
	
	public int modify(MemberDto dto) { // 会員の情報を修正するMethod
		int result = FAIL;
		String sql = "UPDATE MEMBER SET MPW=?, MNAME=?, MBIRTH=?, MGENDER=?, MPHONE=?, MTEL=?, MADDRESS=?, MADETAIL=? WHERE MID=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmPw());
			pstmt.setString(2, dto.getmName());
			pstmt.setTimestamp(3, dto.getmBirth());
			pstmt.setString(4, dto.getmGender());
			pstmt.setString(5, dto.getmPhone());
			pstmt.setString(6, dto.getmTel());
			pstmt.setString(7, dto.getmAddress());
			pstmt.setString(8, dto.getmAdetail());
			pstmt.setString(9, dto.getmId());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return result;
	}
	
	public int delete(String mId) { // 会員脱退するMethod（論理的な削除）
		int result = 0;
		String sql = "UPDATE MEMBER SET LEVELN=? WHERE MID=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, OUTER);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return result;
	}
	
	public int totCnt() { // 会員の数を計算するMethod
		int result = 0;
		String sql = "SELECT COUNT(*) CNT FROM MEMBER";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return result;
	}
	
	public MemberDto view(String mId) { // 会員の詳細情報を見るMethod
		MemberDto dto = null;
		String sql = "SELECT LNAME, A.* FROM MEMBER A, MLEVEL L WHERE A.LEVELN = L.LEVELN AND MID=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDto();
				dto.setmId(rs.getString("mId"));
				dto.setLevelN(rs.getInt("levelN"));
				dto.setlName(rs.getString("lName"));
				dto.setmPw(rs.getString("mPw"));
				dto.setmName(rs.getString("mName"));
				dto.setmBirth(rs.getTimestamp("mBirth"));
				dto.setmGender(rs.getString("mGender"));
				dto.setmPhone(rs.getString("mPhone"));
				dto.setmTel(rs.getString("mTel"));
				dto.setmAddress(rs.getString("mAddress"));
				dto.setmAdetail(rs.getString("mAdetail"));
				dto.setmJoin(rs.getTimestamp("mJoin"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return dto;
	}
	
	public int idCheck(String mId) { // 会員加入する時、同じIDの有無を確認するMethod
		int result = SUCCESS;
		String sql = "SELECT * FROM MEMBER WHERE MID=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = FAIL;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return result;
	}
	
	public String getId(String mName, String mPhone) { // 忘れたIDを探すためのMethod
		String sql = "SELECT MID FROM MEMBER WHERE MNAME=? AND MPHONE=?";
		String mId = "";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mName);
			pstmt.setString(2, mPhone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mId = rs.getString("mId");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return mId;
	}
	
	public String getPassword(String mId, String mPhone) { // 忘れたPasswordを探すためのMethod
		String mPw = "";
		String sql = "SELECT MPW FROM MEMBER WHERE mId=? AND MPHONE=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPhone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mPw = rs.getString("mPw");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return mPw;
	}
	
	public ArrayList<String> searchRank(int levelN){ // 該当するRankの会員Listを貰うMethod
		ArrayList<String> mIds = new ArrayList<String>();
		String sql = "SELECT MID FROM MEMBER WHERE LEVELN=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, levelN);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mIds.add(rs.getString("mId"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return mIds;
	}
	
	public int rankUp(String mId, int levelN) { // 会員のRankを上がるMethod
		String sql = "UPDATE MEMBER SET LEVELN=? WHERE MID=?";
		int result = FAIL;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, levelN-1);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return result;
	}
	
	public int rankAdmin(int levelN, String mId) { // 会員に管理者のRankを与えるMethod
		String sql = "UPDATE MEMBER SET LEVELN=? WHERE MID=?";
		int result = FAIL;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, levelN);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());};
		}
		return result;
	}
}
