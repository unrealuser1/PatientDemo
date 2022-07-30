package com.example.patientdemo.model;

import com.google.gson.annotations.SerializedName;

public class PatientListItem{

	@SerializedName("Category")
	private String category;

	@SerializedName("Address")
	private String address;

	@SerializedName("MobileNo1")
	private String mobileNo1;

	@SerializedName("Test_Result")
	private String testResult;

	@SerializedName("OffSet")
	private String offSet;

	@SerializedName("AdharCardPhoto")
	private String adharCardPhoto;

	@SerializedName("Photo")
	private String photo;

	@SerializedName("Latitude")
	private String latitude;

	@SerializedName("Gender")
	private String gender;

	@SerializedName("Test_Type")
	private String testType;

	@SerializedName("Centre_Name")
	private String centreName;

	@SerializedName("AdharCard_No")
	private String adharCardNo;

	@SerializedName("Longitude")
	private String longitude;

	@SerializedName("Area")
	private String area;

	@SerializedName("Entry_DateTime")
	private String entryDateTime;

	@SerializedName("Patient_UID")
	private int patientUID;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("Centre_Address")
	private String centreAddress;

	@SerializedName("Sample_Id")
	private String sampleId;

	@SerializedName("DailyID")
	private int dailyID;

	@SerializedName("SRF_No")
	private String sRFNo;

	@SerializedName("BirthDate")
	private String birthDate;

	public String getCategory(){
		return category;
	}

	public String getAddress(){
		return address;
	}

	public String getMobileNo1(){
		return mobileNo1;
	}

	public String getTestResult(){
		return testResult;
	}

	public String getOffSet(){
		return offSet;
	}

	public String getAdharCardPhoto(){
		return adharCardPhoto;
	}

	public String getPhoto(){
		return photo;
	}

	public String getLatitude(){
		return latitude;
	}

	public String getGender(){
		return gender;
	}

	public String getTestType(){
		return testType;
	}

	public String getCentreName(){
		return centreName;
	}

	public String getAdharCardNo(){
		return adharCardNo;
	}

	public String getLongitude(){
		return longitude;
	}

	public String getArea(){
		return area;
	}

	public String getEntryDateTime(){
		return entryDateTime;
	}

	public int getPatientUID(){
		return patientUID;
	}

	public String getFullName(){
		return fullName;
	}

	public String getCentreAddress(){
		return centreAddress;
	}

	public String getSampleId(){
		return sampleId;
	}

	public int getDailyID(){
		return dailyID;
	}

	public String getSRFNo(){
		return sRFNo;
	}

	public String getBirthDate(){
		return birthDate;
	}
}