package com.example.mypillsproject.viewModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_pills")
public class PillsItem {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int mg;
    public long timePicker;

    // new Date() -> 1213203012302103 <->
    public PillsItem(String name, int mg, long timePicker) {
        this.name = name;
        this.mg = mg;
        this.timePicker = timePicker;
        /////////////////////////
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMg() {
        return mg;
    }

    public void setMg(int mg) {
        this.mg = mg;
    }

    public long getTime() {
        return timePicker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimePicker(long timePicker) {
        this.timePicker = timePicker;
    }
}

