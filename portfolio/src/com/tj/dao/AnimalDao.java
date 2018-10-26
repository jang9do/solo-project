package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.dto.AnimalDto;

//動物に関するDao
public class AnimalDao {
	public final static int NODELETE=0; // 論理的な削除の変数
	public final static int DELETE=1; // 論理的な削除の変数
	public final static int SUCCESS=1;
	public final static int FAIL=0;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static AnimalDao instance = new AnimalDao(); // シングルトーン
	
	public static AnimalDao getInstance() {
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
	
	public ArrayList<AnimalDto> list(String mId){ // 会員の動物Listを貰うMethod
		ArrayList<AnimalDto> dtos = new ArrayList<AnimalDto>();
		String sql = "SELECT A.*, MNAME FROM ANIMAL A, MEMBER M WHERE A.MID=M.MID AND A.MID=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AnimalDto dto = new AnimalDto();
				dto.setaNum(rs.getInt("aNum"));
				dto.setmId(rs.getString("mId"));
				dto.setmName(rs.getString("mName"));
				dto.setaPicture(rs.getString("aPicture"));
				dto.setaName(rs.getString("aName"));
				dto.setaBirth(rs.getTimestamp("aBirth"));
				dto.setaSpecies(rs.getString("aSpecies"));
				dto.setaSdetail(rs.getString("aSdetail"));
				dto.setaGender(rs.getString("aGender"));
				if(rs.getInt("aDelete")==NODELETE) {
					dtos.add(dto);
				}
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
	
	public ArrayList<AnimalDto> search(String mId, String mName, String aName){ // 管理者の権限を持つ人が動物の情報を探すMethod
		ArrayList<AnimalDto> dtos = new ArrayList<AnimalDto>();
		String sql = "SELECT A.*, MNAME FROM ANIMAL A, MEMBER M WHERE A.MID=M.MID AND A.MID LIKE '%'||?||'%' AND MNAME LIKE '%'||?||'%' AND ANAME LIKE '%'||?||'%'";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			pstmt.setString(3, aName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AnimalDto dto = new AnimalDto();
				dto.setaNum(rs.getInt("aNum"));
				dto.setmId(rs.getString("mId"));
				dto.setmName(rs.getString("mName"));
				dto.setaPicture(rs.getString("aPicture"));
				dto.setaName(rs.getString("aName"));
				dto.setaBirth(rs.getTimestamp("aBirth"));
				dto.setaSpecies(rs.getString("aSpecies"));
				dto.setaSdetail(rs.getString("aSdetail"));
				dto.setaGender(rs.getString("aGender"));
				if(rs.getInt("aDelete")==NODELETE) {
					dtos.add(dto);
				}
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
	
	public AnimalDto view(int aNum){ // 選択した動物の詳細内容を見るMethod
		AnimalDto dto = null;
		String sql = "SELECT A.*, MNAME FROM ANIMAL A, MEMBER M WHERE A.MID=M.MID AND ANUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new AnimalDto();
				dto.setaNum(rs.getInt("aNum"));
				dto.setmId(rs.getString("mId"));
				dto.setmName(rs.getString("mName"));
				dto.setaPicture(rs.getString("aPicture"));
				dto.setaName(rs.getString("aName"));
				dto.setaBirth(rs.getTimestamp("aBirth"));
				dto.setaSpecies(rs.getString("aSpecies"));
				dto.setaSdetail(rs.getString("aSdetail"));
				dto.setaGender(rs.getString("aGender"));
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
	
	public int input(AnimalDto dto) { // ユーザーが新たな動物を登録するMethod
		int result = FAIL;
		String sql = "INSERT INTO ANIMAL VALUES(ANIMAL_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 0)";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getaPicture());
			pstmt.setString(3, dto.getaName());
			pstmt.setTimestamp(4, dto.getaBirth());
			pstmt.setString(5, dto.getaSpecies());
			pstmt.setString(6, dto.getaSdetail());
			pstmt.setString(7, dto.getaGender());
			result = pstmt.executeUpdate();
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
	
	public int modify(AnimalDto dto) { // 登録されている動物の情報を修正するMethod
		int result = FAIL;
		String sql = "UPDATE ANIMAL SET APICTURE=?, ANAME=?, ABIRTH=?, ASPECIES=?, ASDETAIL=?, AGENDER=? WHERE ANUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getaPicture());
			pstmt.setString(2, dto.getaName());
			pstmt.setTimestamp(3, dto.getaBirth());
			pstmt.setString(4, dto.getaSpecies());
			pstmt.setString(5, dto.getaSdetail());
			pstmt.setString(6, dto.getaGender());
			pstmt.setInt(7, dto.getaNum());
			result = pstmt.executeUpdate();
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
	
	public int delete(int aNum) { // 登録している動物を削除するMethod（論理的な削除）
		int result = FAIL;
		String sql = "UPDATE ANIMAL SET ADELETE=1 WHERE ANUM=?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aNum);
			result = pstmt.executeUpdate();
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
}
