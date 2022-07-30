package com.example.patientdemo;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_WEAK;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.patientdemo.model.UserInfoItem;
import com.example.patientdemo.roomDB.Database;

import java.util.List;
import java.util.concurrent.Executor;

public class SplashActivity extends Activity {
    Database db;
    Handler handler;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        db = Database.getInstance(getApplicationContext());
        int userCount = db.dao().getCount();
        handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (userCount > 0) {
//                    Toast.makeText(SplashActivity.this, "Users: "+userCount, Toast.LENGTH_SHORT).show();
                    Intent toAuth = new Intent(SplashActivity.this, WhileAuth.class);
                    finish();
                    startActivity(toAuth);
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);

    }






}


