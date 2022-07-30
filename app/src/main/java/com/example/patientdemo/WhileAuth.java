package com.example.patientdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.patientdemo.roomDB.Database;

import java.util.concurrent.Executor;

public class WhileAuth extends AppCompatActivity {
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_while_auth);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
                    @Override
                    public boolean shouldKeepOnScreen() {
                        return false;
                    }
                });
            }
        }, 3000);
        db = Database.getInstance(getApplicationContext());
        int userCount = db.dao().getCount();
        if (userCount > 0) {
            biometricAuth();
        } else {
            Intent intent = new Intent(WhileAuth.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void biometricAuth(){
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(WhileAuth.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                Intent toDashboard = new Intent(getApplicationContext(), ListActivity.class);
                finish();
                startActivity(toDashboard);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

}