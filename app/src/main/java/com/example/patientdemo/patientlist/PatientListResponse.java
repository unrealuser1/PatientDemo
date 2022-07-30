package com.example.patientdemo.patientlist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PatientListResponse{

	@SerializedName("PatientList")
	private List<PatientListItem> patientList;

	public List<PatientListItem> getPatientList(){
		return patientList;
	}
}