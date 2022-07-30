package com.example.patientdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patientdemo.patientlist.PatientListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    Context context;
    List<PatientListItem> patientList;

    public CustomAdapter(Context context, List<PatientListItem> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    public void filterList(ArrayList<PatientListItem> filterllist) {
        patientList = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patients_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = patientList.get(position).getFullName() +" ("+ patientList.get(position).getGender() +") - "+ patientList.get(position).getMobileNo1();
        holder.nameTv.setText(name);
        holder.centreTv.setText("Centre-  " + patientList.get(position).getCentreName());
        holder.testTv.setText("Test-  " + patientList.get(position).getTestType() + " (" + patientList.get(position).getTestResult() + ")");
        holder.dateTv.setText("Date-  " + patientList.get(position).getEntryDateTime());
        holder.smpIDTv.setText("SMP_ID-  " + patientList.get(position).getSampleId());
        if (patientList.get(position).getGender().equals("F")){
            holder.profile.setImageResource(R.drawable.user_f);
        }
        else{
            holder.profile.setImageResource(R.drawable.user_m);
        }
        ListActivity.pBar.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, centreTv, testTv, dateTv, smpIDTv;
        ImageView profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.card_name);
            centreTv = itemView.findViewById(R.id.card_centre);
            testTv = itemView.findViewById(R.id.card_test);
            dateTv = itemView.findViewById(R.id.card_date);
            smpIDTv = itemView.findViewById(R.id.card_smpid);
            profile = itemView.findViewById(R.id.user_img);
        }
    }
}
