package com.tj.dto;

import java.sql.Timestamp;

public class ReserveDto {
	private int rNum; // 診療予約の番号
	private int aNum; // 診療予約している動物の番号
	private String aName; // 診療予約している動物の名前
	private Timestamp rDate; // 診療予約している日
	private String rDetail; // 望む診療の内容
	
	public ReserveDto() {}
	public ReserveDto(int rNum, int aNum, String aName, Timestamp rDate, String rDetail) {
		super();
		this.rNum = rNum;
		this.aNum = aNum;
		this.aName = aName;
		this.rDate = rDate;
		this.rDetail = rDetail;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public int getaNum() {
		return aNum;
	}
	public void setaNum(int aNum) {
		this.aNum = aNum;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public Timestamp getrDate() {
		return rDate;
	}
	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
	public String getrDetail() {
		return rDetail;
	}
	public void setrDetail(String rDetail) {
		this.rDetail = rDetail;
	}
	@Override
	public String toString() {
		return "ReserveDto [rNum=" + rNum + ", aNum=" + aNum + ", aName=" + aName + ", rDate=" + rDate + ", rDetail="
				+ rDetail + "]";
	}
	
	
}
