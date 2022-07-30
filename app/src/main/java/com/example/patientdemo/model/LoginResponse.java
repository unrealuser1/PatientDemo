package com.example.patientdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

	@SerializedName("UserInfo")
	private List<UserInfoItem> userInfo;

	@SerializedName("error")
	private boolean error;

	@SerializedName("errormsg")
	private String errormsg;

	public List<UserInfoItem> getUserInfo(){
		return userInfo;
	}

	public boolean isError(){
		return error;
	}

	public String getErrormsg(){
		return errormsg;
	}
}