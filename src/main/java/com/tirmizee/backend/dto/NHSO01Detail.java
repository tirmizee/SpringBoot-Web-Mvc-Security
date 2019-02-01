package com.tirmizee.backend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect(
	fieldVisibility = JsonAutoDetect.Visibility.ANY,
	getterVisibility = JsonAutoDetect.Visibility.NONE,
	setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class NHSO01Detail {
	
	private String HMAIN_NAME;
	private String PURCHASEPROVINCE_NAME;
	private String STARTDATE;
	private String HMAIN;
	private String WSID;
	private String WS_STATUS;
	private String MAININSCL_NAME;
	private String CARDID;
	private String PURCHASEPROVINCE;
	private String SUBINSCL;
	private String MAININSCL;
	private String PERSON_ID;
	private String SUBINSCL_NAME;
	
	public String getHMAIN_NAME() {
		return HMAIN_NAME;
	}
	public void setHMAIN_NAME(String hMAIN_NAME) {
		HMAIN_NAME = hMAIN_NAME;
	}
	public String getPURCHASEPROVINCE_NAME() {
		return PURCHASEPROVINCE_NAME;
	}
	public void setPURCHASEPROVINCE_NAME(String pURCHASEPROVINCE_NAME) {
		PURCHASEPROVINCE_NAME = pURCHASEPROVINCE_NAME;
	}
	public String getSTARTDATE() {
		return STARTDATE;
	}
	public void setSTARTDATE(String sTARTDATE) {
		STARTDATE = sTARTDATE;
	}
	public String getHMAIN() {
		return HMAIN;
	}
	public void setHMAIN(String hMAIN) {
		HMAIN = hMAIN;
	}
	public String getWSID() {
		return WSID;
	}
	public void setWSID(String wSID) {
		WSID = wSID;
	}
	public String getWS_STATUS() {
		return WS_STATUS;
	}
	public void setWS_STATUS(String wS_STATUS) {
		WS_STATUS = wS_STATUS;
	}
	public String getMAININSCL_NAME() {
		return MAININSCL_NAME;
	}
	public void setMAININSCL_NAME(String mAININSCL_NAME) {
		MAININSCL_NAME = mAININSCL_NAME;
	}
	public String getCARDID() {
		return CARDID;
	}
	public void setCARDID(String cARDID) {
		CARDID = cARDID;
	}
	public String getPURCHASEPROVINCE() {
		return PURCHASEPROVINCE;
	}
	public void setPURCHASEPROVINCE(String pURCHASEPROVINCE) {
		PURCHASEPROVINCE = pURCHASEPROVINCE;
	}
	public String getSUBINSCL() {
		return SUBINSCL;
	}
	public void setSUBINSCL(String sUBINSCL) {
		SUBINSCL = sUBINSCL;
	}
	public String getMAININSCL() {
		return MAININSCL;
	}
	public void setMAININSCL(String mAININSCL) {
		MAININSCL = mAININSCL;
	}
	public String getPERSON_ID() {
		return PERSON_ID;
	}
	public void setPERSON_ID(String pERSON_ID) {
		PERSON_ID = pERSON_ID;
	}
	public String getSUBINSCL_NAME() {
		return SUBINSCL_NAME;
	}
	public void setSUBINSCL_NAME(String sUBINSCL_NAME) {
		SUBINSCL_NAME = sUBINSCL_NAME;
	}

}
