package com.tj.dto;

import java.sql.Timestamp;

public class MemberDto {
	private String mId; // 会員のID
	private int levelN; // 会員の等級
	private String mPw; // 会員のパースワード
	private String mName; // 会員の名前
	private Timestamp mBirth; // 会員の誕生日
	private String mGender; // 会員の性別
	private String mPhone; // 会員のケータイ番号
	private String mTel; // 会員の電話番号
	private String mAddress; // 会員の居住地
	private String mAdetail; // 会員の詳しい居住地
	private Timestamp mJoin; // 会員の加入日
	private String lName; // 会員の等級の名前
	
	public MemberDto() {}

	public MemberDto(String mId, int levelN, String mPw, String mName, Timestamp mBirth, String mGender, String mPhone,
			String mTel, String mAddress, String mAdetail, Timestamp mJoin, String lName) {
		super();
		this.mId = mId;
		this.levelN = levelN;
		this.mPw = mPw;
		this.mName = mName;
		this.mBirth = mBirth;
		this.mGender = mGender;
		this.mPhone = mPhone;
		this.mTel = mTel;
		this.mAddress = mAddress;
		this.mAdetail = mAdetail;
		this.mJoin = mJoin;
		this.lName = lName;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public int getLevelN() {
		return levelN;
	}

	public void setLevelN(int levelN) {
		this.levelN = levelN;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Timestamp getmBirth() {
		return mBirth;
	}

	public void setmBirth(Timestamp mBirth) {
		this.mBirth = mBirth;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmTel() {
		return mTel;
	}

	public void setmTel(String mTel) {
		this.mTel = mTel;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public String getmAdetail() {
		return mAdetail;
	}

	public void setmAdetail(String mAdetail) {
		this.mAdetail = mAdetail;
	}

	public Timestamp getmJoin() {
		return mJoin;
	}

	public void setmJoin(Timestamp mJoin) {
		this.mJoin = mJoin;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", levelN=" + levelN + ", mPw=" + mPw + ", mName=" + mName + ", mBirth="
				+ mBirth + ", mGender=" + mGender + ", mPhone=" + mPhone + ", mTel=" + mTel + ", mAddress=" + mAddress
				+ ", mAdetail=" + mAdetail + ", mJoin=" + mJoin + ", lName=" + lName + "]";
	}
}
