package com.example.patientdemo.roomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table")
public class Modal {
       @PrimaryKey(autoGenerate = true)
       private int ID;

       @ColumnInfo(name = "number")
       private String number;

       @ColumnInfo(name = "password")
       private String password;

       public int getID() {
              return ID;
       }

       public void setID(int ID) {
              this.ID = ID;
       }

       public String getNumber() {
              return number;
       }

       public void setNumber(String number) {
              this.number = number;
       }

       public String getPassword() {
              return password;
       }

       public void setPassword(String password) {
              this.password = password;
       }
}
