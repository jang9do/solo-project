package com.tj.dto;

import java.sql.Timestamp;

public class AnimalDto {
	private int aNum; // 動物の番号
	private String mId; // 動物の主人のID
	private String aPicture; // 動物の写真
	private String aName; // 動物の名前
	private Timestamp aBirth; // 動物の誕生日
	private String aSpecies; // 動物の種族
	private String aSdetail; // もっと詳しい種族
	private String aGender; // 性別
	private String mName; // 主人の名前
	private int aDelete; // 論理的な削除の変数
	
	public AnimalDto() {}

	public AnimalDto(int aNum, String mId, String aPicture, String aName, Timestamp aBirth, String aSpecies,
			String aSdetail, String aGender, String mName, int aDelete) {
		super();
		this.aNum = aNum;
		this.mId = mId;
		this.aPicture = aPicture;
		this.aName = aName;
		this.aBirth = aBirth;
		this.aSpecies = aSpecies;
		this.aSdetail = aSdetail;
		this.aGender = aGender;
		this.mName = mName;
		this.aDelete = aDelete;
	}

	public int getaNum() {
		return aNum;
	}

	public void setaNum(int aNum) {
		this.aNum = aNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getaPicture() {
		return aPicture;
	}

	public void setaPicture(String aPicture) {
		this.aPicture = aPicture;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public Timestamp getaBirth() {
		return aBirth;
	}

	public void setaBirth(Timestamp aBirth) {
		this.aBirth = aBirth;
	}

	public String getaSpecies() {
		return aSpecies;
	}

	public void setaSpecies(String aSpecies) {
		this.aSpecies = aSpecies;
	}

	public String getaSdetail() {
		return aSdetail;
	}

	public void setaSdetail(String aSdetail) {
		this.aSdetail = aSdetail;
	}

	public String getaGender() {
		return aGender;
	}

	public void setaGender(String aGender) {
		this.aGender = aGender;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getaDelete() {
		return aDelete;
	}

	public void setaDelete(int aDelete) {
		this.aDelete = aDelete;
	}

	@Override
	public String toString() {
		return "AnimalDto [aNum=" + aNum + ", mId=" + mId + ", aPicture=" + aPicture + ", aName=" + aName + ", aBirth="
				+ aBirth + ", aSpecies=" + aSpecies + ", aSdetail=" + aSdetail + ", aGender=" + aGender + ", mName="
				+ mName + ", aDelete=" + aDelete + "]";
	}
}
