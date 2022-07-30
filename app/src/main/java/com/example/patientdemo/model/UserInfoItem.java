package com.example.patientdemo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "UserInfoItem")
public class UserInfoItem{

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@NonNull
	@PrimaryKey(autoGenerate = true)
	private int ID;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@SerializedName("MobileNo")
	@ColumnInfo(name = "MobileNo")
	private String mobileNo;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(int operatorID) {
		this.operatorID = operatorID;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@SerializedName("Designation")
	@ColumnInfo(name = "Designation")
	private String designation;

	@SerializedName("UserName")
	@ColumnInfo(name = "UserName")
	private String userName;

	@SerializedName("Expiry_Date")
	@ColumnInfo(name = "Expiry_Date")
	private String expiryDate;

	@SerializedName("FirstName")
	@ColumnInfo(name = "FirstName")
	private String firstName;

	@SerializedName("IsActive")
	@ColumnInfo(name = "IsActive")
	private int isActive;

	@SerializedName("Photo")
	@ColumnInfo(name = "Photo")
	private String photo;

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String OTP) {
		this.OTP = OTP;
	}

	@SerializedName("OTP")
	@ColumnInfo(name = "OTP")
	private String OTP;

	@SerializedName("Gender")
	@ColumnInfo(name = "Gender")
	private String gender;

	@SerializedName("Operator_ID")
	@ColumnInfo(name = "Operator_ID")
	private int operatorID;

	@SerializedName("MiddleName")
	@ColumnInfo(name = "MiddleName")
	private String middleName;

	@SerializedName("Centre_Name")
	@ColumnInfo(name = "Centre_Name")
	private String centreName;

	@SerializedName("LastName")
	@ColumnInfo(name = "LastName")
	private String lastName;

	@SerializedName("IsVerified")
	@ColumnInfo(name = "IsVerified")
	private int isVerified;

	@SerializedName("UserType")
	@ColumnInfo(name = "UserType")
	private String userType;



}