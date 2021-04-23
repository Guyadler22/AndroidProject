package com.example.mypillsproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mypillsproject.viewModels.PillsItem;

import java.util.Date;

public class AlertDialogUpdate extends AppCompatDialogFragment {

    private PillsItem item;
    private updatePill updatePill;

    public AlertDialogUpdate(PillsItem item, updatePill updatePill) {
        this.item = item;
        this.updatePill = updatePill;
    }

    public PillsItem getItem() {
        return item;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = getLayoutInflater().inflate(R.layout.update_dialog, null);

        final EditText pillName = v.findViewById(R.id.et_name);
        final EditText pillmg = v.findViewById(R.id.et_mg);
        final TimePicker timePicker = v.findViewById(R.id.update_time);
        timePicker.setIs24HourView(true);

        pillmg.setHint(String.valueOf(item.mg));
        pillName.setHint(item.name);
        return new AlertDialog.Builder(getContext()).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel
            }
        }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // update
                String s = pillName.getText().toString();
                String s2 = pillmg.getText().toString();
                if (s.isEmpty() || s2.isEmpty()) {
                    Toast.makeText(getContext(), "Could not update pill", Toast.LENGTH_LONG).show();
                    return;
                }
                int minute = timePicker.getMinute();
                int hour = timePicker.getHour();
                Date date = new Date();
                date.setHours(hour);
                date.setMinutes(minute);
                item.setName(s);
                item.setMg(Integer.parseInt(s2));
                item.setTimePicker(date.getTime());


                updatePill.updatePill(item);
                Toast.makeText(getContext(), "Pill Updated!", Toast.LENGTH_LONG).show();
            }
        }).setView(v).setTitle("Update Pill").create();
    }
}
