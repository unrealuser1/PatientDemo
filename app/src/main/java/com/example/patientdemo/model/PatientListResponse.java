package com.example.patientdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatientListResponse{

	@SerializedName("PatientList")
	private List<PatientListItem> patientList;

	public List<PatientListItem> getPatientList(){
		return patientList;
	}
}