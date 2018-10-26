package com.tj.dto;

import java.sql.Timestamp;

public class BoardDto {
	private int bNum; // 掲示物の番号
	private int baNum; // 掲示物の種類を区別する
	private String mId; // 掲示物を登録した物のID
	private String bName; // 
	private String bIp; // 掲示物を登録した場所のIP
	private Timestamp bDate; // 掲示物を登録した日
	private int bHit; // 掲示物のクリックの数
	private String bTitle; // 掲示物の題目
	private String bContent; // 掲示物の内容
	private int bGroup; // 返事の掲示物の場合、どんな掲示物の返事なのかを表示する
	private int bStep; // 何番目なのかを確認する変数
	private int bIndent; // 返事の掲示物のインデント数
	private String bFile; // 掲示物に添付されているファイル
	
	public BoardDto() {}

	public BoardDto(int bNum, int baNum, String mId, String bName, String bIp, Timestamp bDate, int bHit,
			String bTitle, String bContent, int bGroup, int bStep, int bIndent, String bFile) {
		super();
		this.bNum = bNum;
		this.baNum = baNum;
		this.mId = mId;
		this.bName = bName;
		this.bIp = bIp;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bFile = bFile;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public int getBaNum() {
		return baNum;
	}

	public void setBaNum(int baNum) {
		this.baNum = baNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbIp() {
		return bIp;
	}

	public void setbIp(String bIp) {
		this.bIp = bIp;
	}

	public Timestamp getbDate() {
		return bDate;
	}

	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}

	public String getbFile() {
		return bFile;
	}

	public void setbFile(String bFile) {
		this.bFile = bFile;
	}

	@Override
	public String toString() {
		return "BoardDto [bNum=" + bNum + ", baNum=" + baNum + ", mId=" + mId + ", mName=" + ", bName=" + bName
				+ ", bIp=" + bIp + ", bDate=" + bDate + ", bHit=" + bHit + ", bTitle=" + bTitle + ", bContent="
				+ bContent + ", bGroup=" + bGroup + ", bStep=" + bStep + ", bIndent=" + bIndent + ", bFile=" + bFile
				+ "]";
	}
	
}
