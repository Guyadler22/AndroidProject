package com.example.mypillsproject.viewModels;

import android.widget.DatePicker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_pills")
public class PillsItem {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int mg;
    public long timePicker;
    public DatePicker datePicker;

    public PillsItem(String name, int mg, long timePicker, DatePicker datePicker) {
        this.name = name;
        this.mg = mg;
        this.timePicker = timePicker;
        this.datePicker = datePicker;
        /////////////////////////
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
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

