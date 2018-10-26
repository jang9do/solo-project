package com.tj.dto;

import java.sql.Timestamp;

public class AddBoardDto {
	private int abNum; // Commentの番号
	private int bNum; // Commentが登録された掲示物の番号
	private String mId; // Commentを登録した物のID
	private String abContent; // Commentの内容
	private Timestamp abDate; // Commentを登録した日
	
	public AddBoardDto() {}
	public AddBoardDto(int abNum, int bNum, String mId, String abContent, Timestamp abDate) {
		super();
		this.abNum = abNum;
		this.bNum = bNum;
		this.mId = mId;
		this.abContent = abContent;
		this.abDate = abDate;
	}
	
	public int getAbNum() {
		return abNum;
	}
	public void setAbNum(int abNum) {
		this.abNum = abNum;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getAbContent() {
		return abContent;
	}
	public void setAbContent(String abContent) {
		this.abContent = abContent;
	}
	public Timestamp getAbDate() {
		return abDate;
	}
	public void setAbDate(Timestamp abDate) {
		this.abDate = abDate;
	}
	
	@Override
	public String toString() {
		return "AddBoardDto [abNum=" + abNum + ", bNum=" + bNum + ", mId=" + mId + ", abContent=" + abContent
				+ ", abDate=" + abDate + "]";
	}
}
