package com.example.patientdemo.network;


import com.example.patientdemo.model.LoginResponse;
import com.example.patientdemo.patientlist.PatientListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("patientList2.php")
    Call <PatientListResponse> getpatientlist(
            @Field("resulttype") String resulttype,
            @Field("datatype") String datatype,
            @Field("operatorID") int operatorID,
            @Field("usertype") String usertype,
            @Field("showall") String showall,
            @Field("elecname") String elecname,
            @Field("DevelopmentMode") String  DevelopmentMode,
            @Field("TestType") String  TestType,
            @Field("TestResult") String  TestResult
    );

    @FormUrlEncoded
    @POST("authenticateUser.php")
    Call<LoginResponse> userLogin(
            @Field("mobileNo") String mobileNo,
            @Field("password") String password,
            @Field("appKey") String appKey,
            @Field("ip") String ip,
            @Field("Latitude") String Latitude,
            @Field("Longitude") String Longitude,
            @Field("androidID") String androidID,
            @Field("elecname") String elecname,
            @Field("DevelopmentMode") String DevelopmentMode
    );

}
