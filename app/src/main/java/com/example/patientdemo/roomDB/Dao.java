package com.example.patientdemo.roomDB;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.patientdemo.model.UserInfoItem;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert(onConflict = REPLACE)
    void insert(Modal model);

    @Insert(onConflict = REPLACE)
    void insertUser(List<UserInfoItem> userDetails);

    @Query("SELECT * FROM UserInfoItem")
    List<UserInfoItem> getAll();

    @Query("SELECT COUNT(*) FROM UserInfoItem")
    int getCount();

    @Query("DELETE FROM UserInfoItem")
    void deleteAllFromTable();


}
