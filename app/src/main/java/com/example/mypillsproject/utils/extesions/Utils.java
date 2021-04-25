package com.example.mypillsproject.utils.extesions;

import android.util.Log;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Utils {

    public Utils() {

    }
    public SwitchDateTimeDialogFragment getDateTimeDialog(SwitchDateTimeDialogFragment.OnButtonClickListener listener) {

        SwitchDateTimeDialogFragment dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                "Title example",
                "OK",
                "Cancel"
        );
        dateTimeDialogFragment.startAtCalendarView();
        dateTimeDialogFragment.set24HoursMode(true);
        dateTimeDialogFragment.setMinimumDateTime(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
        dateTimeDialogFragment.setMaximumDateTime(new GregorianCalendar(2025, Calendar.DECEMBER, 31).getTime());
        dateTimeDialogFragment.setDefaultDateTime(new GregorianCalendar(2021, Calendar.MARCH, 4, 15, 20).getTime());
        try {
            dateTimeDialogFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("dd MMMM", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            Log.e("Our Dialog", e.getMessage());
        }
        dateTimeDialogFragment.setOnButtonClickListener(listener);
        return dateTimeDialogFragment;
    }
}
