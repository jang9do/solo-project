package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.dto.ReserveDto;
import com.tj.dto.TreatChartDto;

//診療記録に関するDao
public class TreatChartDao {
	public final static int SUCCESS=1;
	public final static int FAIL=0;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static TreatChartDao instance = new TreatChartDao(); // シングルトーン
	
	public static TreatChartDao getInstance() {
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
	
	public int insert(ReserveDto dto, String tResult) { // 診療記録を登録するMethod
		int result = FAIL;
		String sql = "INSERT INTO TREATCHART VALUES(TREATCHART_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getaNum());
			pstmt.setTimestamp(2, dto.getrDate());
			pstmt.setString(3, dto.getrDetail());
			pstmt.setString(4, tResult);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return result;
	}
	
	public ArrayList<TreatChartDto> search(String aName, String tDate){ // 診療日、診療動物の名前を利用して診療記録を検索するMethod
		ArrayList<TreatChartDto> dtos = new ArrayList<TreatChartDto>();
		String sql = "SELECT ANAME,T.* FROM TREATCHART T, ANIMAL A WHERE T.ANUM=A.ANUM AND ANAME LIKE '%'||?||'%' AND TO_CHAR(TDATE, 'YYYY-MM-DD') LIKE '%'||?||'%'";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aName);
			pstmt.setString(2, tDate);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TreatChartDto dto = new TreatChartDto();
				dto.settNum(rs.getInt("tNum"));
				dto.setaNum(rs.getInt("aNum"));
				dto.setaName(rs.getString("aName"));
				dto.settDate(rs.getTimestamp("tDate"));
				dto.settDetail(rs.getString("tDetail"));
				dto.settResult(rs.getString("tResult"));
				dtos.add(dto);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dtos;
	}
	
	public TreatChartDto read(int tNum){ // 診療記録の内容を見るMethod
		TreatChartDto dto = null;
		String sql = "SELECT ANAME,T.* FROM TREATCHART T, ANIMAL A WHERE T.ANUM=A.ANUM AND TNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new TreatChartDto();
				dto.settNum(rs.getInt("tNum"));
				dto.setaNum(rs.getInt("aNum"));
				dto.setaName(rs.getString("aName"));
				dto.settDate(rs.getTimestamp("tDate"));
				dto.settDetail(rs.getString("tDetail"));
				dto.settResult(rs.getString("tResult"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
		return dto;
	}
}
