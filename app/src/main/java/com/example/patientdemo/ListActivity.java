package com.example.patientdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patientdemo.model.UserInfoItem;
import com.example.patientdemo.network.ApiClient;
import com.example.patientdemo.patientlist.PatientListItem;
import com.example.patientdemo.patientlist.PatientListResponse;
import com.example.patientdemo.roomDB.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    Database database;
    TextView welcomeuser, entriesCount;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    public static ProgressBar pBar;
//    private Executor executor;
//    private BiometricPrompt biometricPrompt;
//    private BiometricPrompt.PromptInfo promptInfo;


    List<PatientListItem> patientList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        welcomeuser = findViewById(R.id.tv_welcome_user);
        callApi();
        recyclerView = findViewById(R.id.recycler_view);
        entriesCount = findViewById(R.id.tv_entries_count);
        database = Database.getInstance(getApplicationContext());
        List<UserInfoItem> userInfoItems = database.dao().getAll();
        for (UserInfoItem items:userInfoItems){
            String fName = "Welcome " + items.getFirstName();
            welcomeuser.setText(fName);
        }
        sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        pBar = findViewById(R.id.progressbar);

//        biometricAuth();
    }

    private void callApi() {
        Call<PatientListResponse> call = ApiClient
                .getInstance().getApi().getpatientlist(
                        "Total",
                        "Today",
                        1,
                        "A",
                        "true",
                        "NMMC",
                        "Live",
                        "ALL",
                        "ALL"
                );

        call.enqueue(new Callback<PatientListResponse>() {
            @Override
            public void onResponse(Call<PatientListResponse> call,
                                   Response<PatientListResponse> response) {
                PatientListResponse patientllist = response.body();
                if (patientllist != null) {
                    patientList = patientllist.getPatientList();
                    for (int i = 0; i < patientList.size(); i++) {
                        patientList.get(i).getFullName();
                        patientList.get(i).getCentreName();
                        patientList.get(i).getTestType();
                        patientList.get(i).getEntryDateTime();
                        patientList.get(i).getSampleId();
                    }
                    entriesCount.setText("Record found: " + patientList.size());
                    adapter = new CustomAdapter(getApplicationContext(), patientList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PatientListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please Check Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void filter(String text) {
        ArrayList<PatientListItem> filteredlist = new ArrayList<>();

        for (PatientListItem item : patientList) {
            if (item.getFullName().toLowerCase().contains(text.toLowerCase())
                    || item.getTestType().toLowerCase().contains(text.toLowerCase())
                    || item.getMobileNo1().contains(text.toLowerCase())
                    || item.getTestResult().toLowerCase().contains(text.toLowerCase()))
            {
                filteredlist.add(item);
            }
        }

//        if (filteredlist.isEmpty()) {
//            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
//        } else {
            adapter.filterList(filteredlist);
        //}
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                logOut();
                return true;
            case R.id.action_search:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key", 0);
        editor.apply();

        database.dao().deleteAllFromTable();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

//    public void biometricAuth(){
//        executor = ContextCompat.getMainExecutor(this);
//        biometricPrompt = new BiometricPrompt(ListActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
//            @Override
//            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
//                super.onAuthenticationError(errorCode, errString);
//                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAuthenticationSucceeded(
//                    @NonNull BiometricPrompt.AuthenticationResult result) {
//                super.onAuthenticationSucceeded(result);
//                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAuthenticationFailed() {
//                super.onAuthenticationFailed();
//                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//        promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Biometric login for my app")
//                .setSubtitle("Log in using your biometric credential")
//                .setNegativeButtonText("Use account password")
//                .build();
//
//        biometricPrompt.authenticate(promptInfo);
//    }

}