package com.example.patientdemo.roomDB;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.patientdemo.model.UserInfoItem;


@androidx.room.Database(entities = {Modal.class, UserInfoItem.class}, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public static Database database;
    public static synchronized Database getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context,
                        Database.class, "login_table")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        }
        return database;
    }
    public abstract Dao dao();
}
