package com.tj.dto;

import java.sql.Timestamp;

public class MessageDto {
	private int mesNum; // Messageの番号
	private String mId; // 貰う人のID
	private String mesTitle; // Messageの題目
	private String mesCon; // Messageの内容
	private String mesFrom; // 送った人のID
	private Timestamp mesDate; // Messageを送った時間
	private int mesRead; // Messageを読むことの有無
	private String mName; // Messageを貰った人の名前
	
	public MessageDto() {}
	public MessageDto(int mesNum, String mId, String mesTitle, String mesCon, String mesFrom, Timestamp mesDate,
			int mesRead, String mName) {
		super();
		this.mesNum = mesNum;
		this.mId = mId;
		this.mesTitle = mesTitle;
		this.mesCon = mesCon;
		this.mesFrom = mesFrom;
		this.mesDate = mesDate;
		this.mesRead = mesRead;
		this.mName = mName;
	}
	
	public int getMesNum() {
		return mesNum;
	}
	public void setMesNum(int mesNum) {
		this.mesNum = mesNum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getMesTitle() {
		return mesTitle;
	}
	public void setMesTitle(String mesTitle) {
		this.mesTitle = mesTitle;
	}
	public String getMesCon() {
		return mesCon;
	}
	public void setMesCon(String mesCon) {
		this.mesCon = mesCon;
	}
	public String getMesFrom() {
		return mesFrom;
	}
	public void setMesFrom(String mesFrom) {
		this.mesFrom = mesFrom;
	}
	public Timestamp getMesDate() {
		return mesDate;
	}
	public void setMesDate(Timestamp mesDate) {
		this.mesDate = mesDate;
	}
	public int getMesRead() {
		return mesRead;
	}
	public void setMesRead(int mesRead) {
		this.mesRead = mesRead;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	@Override
	public String toString() {
		return "MessageDto [mesNum=" + mesNum + ", mId=" + mId + ", mesTitle=" + mesTitle + ", mesCon=" + mesCon
				+ ", mesFrom=" + mesFrom + ", mesDate=" + mesDate + ", mesRead=" + mesRead + ", mName=" + mName + "]";
	}
}
