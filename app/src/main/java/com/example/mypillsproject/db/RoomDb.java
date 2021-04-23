package com.example.mypillsproject.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mypillsproject.viewModels.PillsItem;


@Database(entities = {PillsItem.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {

    //create database instance
    private static RoomDb roomDb;

    //define database name
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDb getInstance(Context context) {
        //check condition
        // when database is null
        if (roomDb == null) {
            //Initialize database
            roomDb = Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, DATABASE_NAME).allowMainThreadQueries().
                    fallbackToDestructiveMigration().build();
            //return database
        }
        return roomDb;
        //Create Dao
    }

    public abstract ItemDao itemDao();
}
