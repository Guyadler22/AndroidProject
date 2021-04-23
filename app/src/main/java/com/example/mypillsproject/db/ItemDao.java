package com.example.mypillsproject.db;


import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mypillsproject.viewModels.PillsItem;

import java.util.List;

@androidx.room.Dao
public interface ItemDao {

    @Insert
    void InsertPillsItem(PillsItem pillsItem);

    @Query("SELECT * FROM 'table_pills' ")
    LiveData<List<PillsItem>> getAllPillsItem();

    @Update
    void updateItem(PillsItem pillsItem);

    @Delete
    void deleteItem(PillsItem pillsItem);
}
