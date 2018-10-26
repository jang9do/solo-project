package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.dto.MessageDto;

//Messagに関するDao
public class MessageDao {
	public final static int SUCCESS=1;
	public final static int FAIL=0;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static MessageDao instance = new MessageDao(); // シングルトーン
	
	public static MessageDao getInstance() {
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
	
	public MessageDto read(int mesNum) { // Messageの内容を見るMethod
		MessageDto dto = null;
		String sql = "SELECT MNAME ,MES.* FROM MESSAGE MES, MEMBER M WHERE M.MID=MES.MESFROM AND MESNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mesNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MessageDto();
				dto.setMesNum(rs.getInt("mesNum"));
				dto.setmId(rs.getString("mId"));
				dto.setmName(rs.getString("mName"));
				dto.setMesTitle(rs.getString("mesTitle"));
				dto.setMesCon(rs.getString("mesCon"));
				dto.setMesFrom(rs.getString("mesFrom"));
				dto.setMesDate(rs.getTimestamp("mesDate"));
				dto.setMesRead(rs.getInt("mesRead"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dto;
	}
	
	public int readChk(int mesNum) { // 内容を見たMessageにチェックするMethod
		int result = FAIL;
		String sql = "UPDATE MESSAGE SET MESREAD=1 WHERE MESNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mesNum);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		
		return result;
	}
	
	public int noReadChk(String mId) { // 内容を見てないMessageのListを貰うMethod
		int result = FAIL;
		String sql = "SELECT COUNT(*) COUNT FROM MESSAGE WHERE MESFROM=? AND MESREAD=0";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		
		return result;
	}
	
	public ArrayList<MessageDto> send_search(int startRow, int endRow, String mId){ // 送った会員のIDでMessageを検索するMethod
		ArrayList<MessageDto> dtos = new ArrayList<MessageDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT MNAME, MES.* FROM MESSAGE MES, MEMBER M WHERE MES.MESFROM=M.MID AND MES.MID=? ORDER BY MESDATE DESC) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MessageDto dto = new MessageDto();
				dto.setMesNum(rs.getInt("mesNum"));
				dto.setmId(rs.getString("mId"));
				dto.setmName(rs.getString("mName"));
				dto.setMesTitle(rs.getString("mesTitle"));
				dto.setMesCon(rs.getString("mesCon"));
				dto.setMesFrom(rs.getString("mesFrom"));
				dto.setMesDate(rs.getTimestamp("mesDate"));
				dto.setMesRead(rs.getInt("mesRead"));
				dtos.add(dto);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dtos;
	}
	public int send_count(String mId) { // 自分が送ったMessageを数を計算するMethod
		int result = FAIL;
		String sql = "SELECT COUNT(*) COUNT FROM MESSAGE WHERE MID=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return result;
	}
	
	public ArrayList<MessageDto> receive_search(int startRow, int endRow, String mesFrom){ // 貰ったMessageのListを貰ってくるMethod
		ArrayList<MessageDto> dtos = new ArrayList<MessageDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT MNAME, MES.* FROM MESSAGE MES, MEMBER M WHERE MES.MID=M.MID AND MESFROM=? ORDER BY MESDATE DESC) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mesFrom);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MessageDto dto = new MessageDto();
				dto.setMesNum(rs.getInt("mesNum"));
				dto.setmId(rs.getString("mId"));
				dto.setmName(rs.getString("mName"));
				dto.setMesTitle(rs.getString("mesTitle"));
				dto.setMesCon(rs.getString("mesCon"));
				dto.setMesFrom(rs.getString("mesFrom"));
				dto.setMesDate(rs.getTimestamp("mesDate"));
				dto.setMesRead(rs.getInt("mesRead"));
				dtos.add(dto);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dtos;
	}
	public int receive_count(String mesFrom) { // 貰ったMessageの数を計算するMethod
		int result = FAIL;
		String sql = "SELECT COUNT(*) COUNT FROM MESSAGE WHERE MESFROM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mesFrom);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return result;
	}
	
	public int send(MessageDto dto) { // Messageを数を送るMethod
		int result = FAIL;
		String sql = "INSERT INTO MESSAGE VALUES(MESSAGE_SEQ.NEXTVAL, ?, ?, ?, ?, sysdate, 0)";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getMesTitle());
			pstmt.setString(3, dto.getMesCon());
			pstmt.setString(4, dto.getMesFrom());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		
		return result;
	}
	
	public MessageDto view(int mesNum) { // 送ったMessageを見るMethod
		MessageDto dto = null;
		String sql = "SELECT MNAME, MES.* FROM MESSAGE MES, MEMBER M WHERE MES.MID=M.MID AND MESNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mesNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MessageDto();
				dto.setMesNum(rs.getInt("mesNum"));
				dto.setmId(rs.getString("mId"));
				dto.setmName(rs.getString("mName"));
				dto.setMesTitle(rs.getString("mesTitle"));
				dto.setMesCon(rs.getString("mesCon"));
				dto.setMesFrom(rs.getString("mesFrom"));
				dto.setMesDate(rs.getTimestamp("mesDate"));
				dto.setMesRead(rs.getInt("mesRead"));
				readChk(mesNum);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dto;
	}
}
