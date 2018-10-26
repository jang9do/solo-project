package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.dto.BoardDto;

//掲示板に関するDao（公知、Best、一般の掲示物が糸つのテーブルにある）
public class BoardDao {
	public final static int TOP = 1;
	public final static int HIT_Q=2;
	public final static int SUCCESS=1;
	public final static int FAIL=0;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static BoardDao instance = new BoardDao(); // シングルトーン
	
	public static BoardDao getInstance() {
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
	
	public ArrayList<BoardDto> list(int startRow, int endRow){ // 掲示物のListを貰うMethod
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BANUM!=1 ORDER BY BGROUP DESC, BSTEP) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setbNum(rs.getInt("bNum"));
				dto.setBaNum(rs.getInt("baNum"));
				dto.setmId(rs.getString("mId"));
				dto.setbName(rs.getString("bName"));
				dto.setbIp(rs.getString("bIp"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbFile(rs.getString("bFile"));
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
	
	public ArrayList<BoardDto> topList(int startRow, int endRow){ // 公知掲示物のListを貰うMethod、BANUM=1が公知
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BANUM=1 ORDER BY BNUM DESC) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setbNum(rs.getInt("bNum"));
				dto.setBaNum(rs.getInt("baNum"));
				dto.setmId(rs.getString("mId"));
				dto.setbName(rs.getString("bName"));
				dto.setbIp(rs.getString("bIp"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbFile(rs.getString("bFile"));
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
	
	public ArrayList<BoardDto> top5List(){ // 公知掲示物の上位の五つのListを貰うMethod、BANUM=1が公知
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM BOARD WHERE BANUM=1 ORDER BY BNUM DESC";
		try {
			int i = 0;
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next() && i<5) {
				BoardDto dto = new BoardDto();
				dto.setbNum(rs.getInt("bNum"));
				dto.setBaNum(rs.getInt("baNum"));
				dto.setmId(rs.getString("mId"));
				dto.setbName(rs.getString("bName"));
				dto.setbIp(rs.getString("bIp"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbFile(rs.getString("bFile"));
				dtos.add(dto);
				i++;
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
	
	public ArrayList<BoardDto> bestList(int startRow, int endRow){ // Best掲示物のListを貰うMethod、BANUM=2がBest
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BANUM=2 ORDER BY BNUM DESC) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setbNum(rs.getInt("bNum"));
				dto.setBaNum(rs.getInt("baNum"));
				dto.setmId(rs.getString("mId"));
				dto.setbName(rs.getString("bName"));
				dto.setbIp(rs.getString("bIp"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbFile(rs.getString("bFile"));
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
	
	public ArrayList<BoardDto> best5List(){ // Best掲示物の上位の五つのListを貰うMethod、BANUM=２がBest
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM BOARD WHERE BANUM=2 ORDER BY BNUM DESC";
		try {
			int i = 0;
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next() && i<5) {
				BoardDto dto = new BoardDto();
				dto.setbNum(rs.getInt("bNum"));
				dto.setBaNum(rs.getInt("baNum"));
				dto.setmId(rs.getString("mId"));
				dto.setbName(rs.getString("bName"));
				dto.setbIp(rs.getString("bIp"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbFile(rs.getString("bFile"));
				dtos.add(dto);
				i++;
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
	
	public int insert(BoardDto dto) { // 掲示物を登録するMethod
		int result = FAIL;
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, 0, ?, ?, BOARD_SEQ.NEXTVAL, 0, 0, ?)";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBaNum());
			pstmt.setString(2, dto.getmId());
			pstmt.setString(3, dto.getbName());
			pstmt.setString(4, dto.getbIp());
			pstmt.setString(5, dto.getbTitle());
			pstmt.setString(6, dto.getbContent());
			pstmt.setString(7, dto.getbFile());
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
	
	public int modify(BoardDto dto) { // 掲示物を修正するMethod
		int result = FAIL;
		String sql = "UPDATE BOARD SET BANUM=?, BNAME=?, BTITLE=?, BCONTENT=?, BFILE=? WHERE BNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBaNum());
			pstmt.setString(2, dto.getbName());
			pstmt.setString(3, dto.getbTitle());
			pstmt.setString(4, dto.getbContent());
			pstmt.setString(5, dto.getbFile());
			pstmt.setInt(6, dto.getbNum());
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
	
	public void stepUp(int bGroup, int bStep) { // 返事の掲示物を登録する時、インデントを計算するMethod
		String sql = "UPDATE BOARD SET BSTEP = BSTEP+1 WHERE BGROUP=? AND BSTEP>?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bGroup);
			pstmt.setInt(2, bStep);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
	}
	
	public int reInsert(BoardDto dto) { // 返事の掲示物を登録するMethod
		int result = FAIL;
		stepUp(dto.getbGroup(), dto.getbStep());
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBaNum());
			pstmt.setString(2, dto.getmId());
			pstmt.setString(3, dto.getbName());
			pstmt.setString(4, dto.getbIp());
			pstmt.setTimestamp(5, dto.getbDate());
			pstmt.setString(6, dto.getbTitle());
			pstmt.setString(7, dto.getbContent());
			pstmt.setInt(8, dto.getbGroup());
			pstmt.setInt(9, dto.getbStep());
			pstmt.setInt(10, dto.getbIndent());
			pstmt.setString(11, dto.getbFile());
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
	
	public BoardDto view(int bNum) { // 掲示物の内容を見るMethod
		BoardDto dto = null;
		hitUp(bNum);
		String sql = "SELECT * FROM BOARD WHERE BNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BoardDto();
				dto.setbNum(rs.getInt("bNum"));
				dto.setBaNum(rs.getInt("baNum"));
				dto.setmId(rs.getString("mId"));
				dto.setbName(rs.getString("bName"));
				dto.setbIp(rs.getString("bIp"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbFile(rs.getString("bFile"));
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
	
	public int totCntG() { // 公知の掲示物の数を計算するMethod
		int result = FAIL;
		String sql = "SELECT COUNT(*) COUNT FROM BOARD WHERE BANUM=1";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
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
		return result;
	}
	public int totCntB() { // Best掲示物の数を計算するMethod
		int result = FAIL;
		String sql = "SELECT COUNT(*) COUNT FROM BOARD WHERE BANUM=2";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
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
		return result;
	}
	public int totCntN() { // 一般の掲示物の数を計算するMethod
		int result = FAIL;
		String sql = "SELECT COUNT(*) COUNT FROM BOARD WHERE BANUM!=1";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
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
		return result;
	}
	
	public int totCntS(int rank, String value) { // Bestと一般の掲示物を合わせた数を計算するMethod
		int result = FAIL;
		String sql;
		if(rank == 1) {
			sql = "SELECT COUNT(*) COUNT FROM BOARD WHERE BANUM!=1 AND BTITLE LIKE '%'||?||'%'";
		} else if(rank == 2) {
			sql = "SELECT COUNT(*) COUNT FROM BOARD WHERE BANUM!=1 AND BCONTENT LIKE '%'||?||'%'";
		} else if(rank == 3) {
			sql = "SELECT COUNT(*) COUNT FROM BOARD WHERE BANUM!=1 AND (BTITLE LIKE '%'||?||'%' OR BCONTENT LIKE '%'||?||'%')";
		} else {
			sql = "";
		}
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			if(rank == 3) {
				pstmt.setString(2, value);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
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
		return result;
	}
	
	public void hitUp(int bNum) { // 掲示物のクリック回数を上がるMethod
		String sql = "UPDATE BOARD SET BHIT = BHIT+1 WHERE BNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {System.out.println(e2.getMessage());}
		}
	}
	
	public int delete(int bNum) { // 掲示物を削除するMethod
		AddBoardDao addDao = AddBoardDao.getInstance(); // CommentのDao
		int result = FAIL;
		addDao.delete_Page(bNum); // 先にこの掲示物に登録されている全てのCommentを消す
		String sql = "DELETE FROM BOARD WHERE BNUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
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
	
	public ArrayList<BoardDto> Search(int rank, String value, int startRow, int endRow) { // 掲示物を検索するMethod
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		String sql;
		
		if(rank == 1) { // 掲示物の名前で検索
			sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BANUM!=1 AND BTITLE LIKE '%'||?||'%' ORDER BY BGROUP DESC, BSTEP) A) WHERE RN BETWEEN ? AND ?";
		} else if(rank == 2) { // 掲示物の内容で検索
			sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BANUM!=1 AND BCONTENT LIKE '%'||?||'%' ORDER BY BGROUP DESC, BSTEP) A) WHERE RN BETWEEN ? AND ?";
		} else if(rank == 3) { // 掲示物の名前、及び内容で検索
			sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BANUM!=1 AND (BTITLE LIKE '%'||?||'%' OR BCONTENT LIKE '%'||?||'%') ORDER BY BGROUP DESC, BSTEP) A) WHERE RN BETWEEN ? AND ?";
		} else {
			sql = "";
		}
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			if(rank==1 || rank==2) {
				pstmt.setString(1, value);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else {
				pstmt.setString(1, value);
				pstmt.setString(2, value);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setbNum(rs.getInt("bNum"));
				dto.setBaNum(rs.getInt("baNum"));
				dto.setmId(rs.getString("mId"));
				dto.setbName(rs.getString("bName"));
				dto.setbIp(rs.getString("bIp"));
				dto.setbDate(rs.getTimestamp("bDate"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbContent(rs.getString("bContent"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				dto.setbFile(rs.getString("bFile"));
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
	
	public int best(int bNum) { // 一般掲示物をBestにするMethod
		String sql = "UPDATE BOARD SET BANUM=2 WHERE BNUM=?";
		int result = FAIL;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
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
}
