package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.dto.AddBoardDto;

//掲示板のCommentに関するDao
public class AddBoardDao {
	public final static int SUCCESS=1;
	public final static int FAIL=0;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static AddBoardDao instance = new AddBoardDao(); // シングルトーン
	
	public static AddBoardDao getInstance() {
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
	
	public ArrayList<AddBoardDto> list(int bNum){ // 掲示物のComment Listを貰うMethod
		ArrayList<AddBoardDto> dtos = new ArrayList<AddBoardDto>();
		String sql = "SELECT * FROM ADDBOARD WHERE BNUM = ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AddBoardDto dto = new AddBoardDto();
				dto.setAbNum(rs.getInt("abNum"));
				dto.setbNum(rs.getInt("bNum"));
				dto.setmId(rs.getString("mId"));
				dto.setAbContent(rs.getString("abContent"));
				dto.setAbDate(rs.getTimestamp("abDate"));
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
	
	public int insert(int bNum, String mId, String abContent) { // 掲示物のCommentを登録するMethod
		int result = FAIL;
		String sql ="INSERT INTO ADDBOARD VALUES(ADDBOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			pstmt.setString(2, mId);
			pstmt.setString(3, abContent);
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
	
	public int delete(int abNum) { // 掲示物のCommentを消すMethod
		int result = FAIL;
		String sql ="DELETE FROM ADDBOARD WHERE ABNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, abNum);
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
	
	public void delete_Page(int bNum) { // 掲示物を消す時それに登録している全てのCommentを消すMethod
		String sql ="DELETE FROM ADDBOARD WHERE BNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
	}
}
