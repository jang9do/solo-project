package com.tj.dto;

import java.sql.Timestamp;

public class TreatChartDto {
	private int tNum; // 診療記録の番号
	private int aNum; // 診療を貰った動物の番号
	private String aName; // 診療を貰った動物の名前
	private Timestamp tDate; // 診療を貰った日
	private String tDetail; // 貰った診療の内容
	private String tResult; // 診療後の結果、及び処方
	
	public TreatChartDto() {}
	public TreatChartDto(int tNum, int aNum, String aName, Timestamp tDate, String tDetail, String tResult) {
		super();
		this.tNum = tNum;
		this.aNum = aNum;
		this.aName = aName;
		this.tDate = tDate;
		this.tDetail = tDetail;
		this.tResult = tResult;
	}
	public int gettNum() {
		return tNum;
	}
	public void settNum(int tNum) {
		this.tNum = tNum;
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
	public Timestamp gettDate() {
		return tDate;
	}
	public void settDate(Timestamp tDate) {
		this.tDate = tDate;
	}
	public String gettDetail() {
		return tDetail;
	}
	public void settDetail(String tDetail) {
		this.tDetail = tDetail;
	}
	public String gettResult() {
		return tResult;
	}
	public void settResult(String tResult) {
		this.tResult = tResult;
	}
	@Override
	public String toString() {
		return "TreatChartDto [tNum=" + tNum + ", aNum=" + aNum + ", aName=" + aName + ", tDate=" + tDate + ", tDetail="
				+ tDetail + ", tResult=" + tResult + "]";
	}
}
