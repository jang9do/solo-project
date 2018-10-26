package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.dto.ReserveDto;

//診療予約に関するDao
public class ReserveDao {
	public final static int SUCCESS=1;
	public final static int FAIL=0;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static ReserveDao instance = new ReserveDao(); // シングルトーン
	
	public static ReserveDao getInstance() {
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
	
	public int reserve(ReserveDto dto) { // 診療予約をするMethod
		int result = reserveChk(dto.getrDate().toString().substring(0, 16));
		String sql = "INSERT INTO RESERVE VALUES(RESERVE_SEQ.NEXTVAL, ?, ?, ?)";
		if(result==SUCCESS) {
			try {
				conn = getConn();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getaNum());
				pstmt.setTimestamp(2, dto.getrDate());
				pstmt.setString(3, dto.getrDetail());
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch(Exception e2) {System.out.println(e2.getMessage());}
			}
		}
		
		return result;
	}
	public int reserveChk(String rDate) { // 該当する時間に診療予約を有無を確認するMethod
		int result = SUCCESS;
		String sql = "SELECT * FROM RESERVE WHERE RDATE = TO_DATE(?,'YYYY-MM-DD HH24:MI')";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rDate);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = FAIL;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		
		return result;
	}
	
	public ReserveDto read(int rNum) { // 予約の詳細内容を見るMethod
		ReserveDto dto = null;
		String sql = "SELECT ANAME, R.* FROM RESERVE R, ANIMAL A WHERE R.ANUM=A.ANUM AND RNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new ReserveDto();
				dto.setrNum(rs.getInt("rNum"));
				dto.setaNum(rs.getInt("aNum"));
				dto.setaName(rs.getString("aName"));
				dto.setrDate(rs.getTimestamp("rDate"));
				dto.setrDetail(rs.getString("rDetail"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		
		return dto;
	}
	
	public ArrayList<ReserveDto> daylist(String day){ // 該当する日の予約Listを貰うMethod
		ArrayList<ReserveDto> dtos = new ArrayList<ReserveDto>();
		String sql = "SELECT ANAME, R.* FROM RESERVE R, ANIMAL A WHERE R.ANUM=A.ANUM AND TO_CHAR(R.RDATE, 'YYYY-MM-DD')=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, day);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReserveDto dto = new ReserveDto();
				dto.setrNum(rs.getInt("rNum"));
				dto.setaNum(rs.getInt("aNum"));
				dto.setaName(rs.getString("aName"));
				dto.setrDate(rs.getTimestamp("rDate"));
				dto.setrDetail(rs.getString("rDetail"));
				dtos.add(dto);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dtos;
	}
	
	public ArrayList<ReserveDto> mIdlist(String mId){ // 会員の予約Listを貰うMethod
		ArrayList<ReserveDto> dtos = new ArrayList<ReserveDto>();
		String sql = "SELECT ANAME,R.* FROM RESERVE R, ANIMAL A, MEMBER M WHERE R.ANUM=A.ANUM AND A.MID=M.MID AND M.MID=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReserveDto dto = new ReserveDto();
				dto.setrNum(rs.getInt("rNum"));
				dto.setaNum(rs.getInt("aNum"));
				dto.setaName(rs.getString("aName"));
				dto.setrDate(rs.getTimestamp("rDate"));
				dto.setrDetail(rs.getString("rDetail"));
				dtos.add(dto);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dtos;
	}
	
	public int delete(int rNum) { // 予約を消すMethod
		int result = FAIL;
		String sql = "DELETE FROM RESERVE WHERE RNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNum);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		
		return result;
	}
	
	public int reserveFinish(int rNum, String tResult) { // 予約した診療が終わると実行するMethod（該当の予約は消す）
		TreatChartDao tDao = TreatChartDao.getInstance(); // 診療記録のDao
		ReserveDto dto = read(rNum); // 予約情報を貯槽
		
		int result = delete(rNum); // 予約を消す
		
		if(result==SUCCESS) {
			result = tDao.insert(dto, tResult); // 診療記録を残す
		} else {
			result = FAIL;
		}
		
		return result;
	}
}
