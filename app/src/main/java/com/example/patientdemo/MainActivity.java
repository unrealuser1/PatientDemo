package com.example.patientdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.patientdemo.model.LoginResponse;
import com.example.patientdemo.model.UserInfoItem;
import com.example.patientdemo.network.ApiClient;
import com.example.patientdemo.roomDB.Database;
import com.example.patientdemo.roomDB.Modal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText number, password;
    Button loginButton;
    String TEXTnumber, TEXTpassword;
    Database database;
    SharedPreferences sharedpreferences;
    int autoSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();

        sharedpreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        int isLoggedIn = sharedpreferences.getInt("key", 0);
        if(isLoggedIn > 0){
            Intent activity = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(activity);
        }

        loginButton.setOnClickListener(view->{
            getTextData();
            if (TEXTnumber.isEmpty() && TEXTpassword.isEmpty()){
                Toast.makeText(this, "Enter all details!", Toast.LENGTH_SHORT).show();
            }
            else if (TEXTpassword.isEmpty()){
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            }
            else{
                login();
            }
        });

    }



    public void login() {
        getTextData();
        //CHECK INTERNET
        if (!checkInternetConnection(this)){
            Toast.makeText(this, "OOPS! NO INTERNET", Toast.LENGTH_SHORT).show();
        }
        else {
            //API
            Call<LoginResponse> loginResponseCall = ApiClient.getInstance().getApi()
                    .userLogin(TEXTnumber,
                            TEXTpassword,
                            "d4h85d4",
                            "192.168.2.1",
                            "19.08295323994856",
                            "73.0063350483548",
                            "1195151652181519",
                            "NMMC",
                            "Testing"
                    );
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isError()) {
                        Toast.makeText(MainActivity.this, loginResponse.getErrormsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        List<UserInfoItem> userInfo = loginResponse.getUserInfo();
                        database.dao().insertUser(userInfo);
                        Intent toListAct = new Intent(getApplicationContext(), ListActivity.class);
                        startActivity(toListAct);
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("Login ----->", t.toString());
                    Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                    call.cancel();
                }
            });

            getTextData();
            database = Database.getInstance(getApplicationContext());
            Modal data = new Modal();
            data.setNumber(TEXTnumber);
            data.setPassword(TEXTpassword);
            database.dao().insert(data);


            // SHARED PREFS / LOGIN SETTINGS
            autoSave = 1;
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt("key", autoSave);
            editor.apply();
        }

    }
    private void getTextData() {
        TEXTnumber = number.getText().toString();
        TEXTpassword = password.getText().toString();
    }
    private void initializeView() {
        number = findViewById(R.id.login_number);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.btn_login);
    }
    public static boolean checkInternetConnection(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected() && cm.getActiveNetworkInfo().isConnectedOrConnecting();
            } else {
                return false;
            }
        } else {
            return false;
        }

    }



}