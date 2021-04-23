package com.example.mypillsproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mypillsproject.ui.fragments.mainFragment.HomeFragment;

import java.util.Date;

public class DialogPopUp extends AppCompatDialogFragment  {

    TimePicker timePicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.set_notification, null);
        builder.setView(view).setTitle("Choose time").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int minute = timePicker.getMinute();
                int hour = timePicker.getHour();
                Date date = new Date();
                date.setHours(hour);
                date.setMinutes(minute);
                HomeFragment.TIME_VALUE = date;
            }
        });

        timePicker = view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        return builder.create();

    }
}
