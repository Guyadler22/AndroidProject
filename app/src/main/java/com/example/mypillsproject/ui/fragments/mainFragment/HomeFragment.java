package com.example.mypillsproject.ui.fragments.mainFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mypillsproject.DialogPopUp;
import com.example.mypillsproject.R;
import com.example.mypillsproject.viewModels.HomeViewModel;
import com.example.mypillsproject.viewModels.PillsItem;

import java.util.Date;

public class HomeFragment extends Fragment {

    Button nextBTN;
    SwitchCompat setNote, setDate;
    EditText pillName, pillMg;
    HomeViewModel model;

    public static Date TIME_VALUE;
    public static DatePicker DATE_VALUE;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDate = view.findViewById(R.id.setDate);
        setDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked)
//                    showDialogForDate();
            }
        });
        // initialize dialog buttons
        //initialize the switch
        setNote = view.findViewById(R.id.setNotification);
        setNote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    showDialogForTime();
            }
        });

        //initialize the edit text and btn
        pillMg = view.findViewById(R.id.pill_mgT);
        pillName = view.findViewById(R.id.pill_NameTake);
        nextBTN = view.findViewById(R.id.nextBTN);
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pillMg.getText().toString().isEmpty() || pillName.getText().toString().isEmpty() || !setNote.isChecked() || !setDate.isChecked()) {
                    if (pillMg.getText().toString().isEmpty()) {
                        pillMg.requestFocus();
                    }
                    if (pillName.getText().toString().isEmpty()) {
                        pillName.requestFocus();
                    }
                    AlertDialog dialog = new AlertDialog.Builder(getContext()).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setTitle("Please fill all the fields").create();
                    dialog.show();
                    return;
                }

                System.err.println(TIME_VALUE);
                final int mg = Integer.parseInt(pillMg.getText().toString());
                final String pName = pillName.getText().toString();
                model.insertPill(new PillsItem(pName, mg, TIME_VALUE.getTime(),null));

                nextBTN.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.PillsFragment, null));
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void showDialogForTime() {
        DialogPopUp dialogPopUp = new DialogPopUp();
        dialogPopUp.show(getParentFragmentManager(), "");
    }

//    public void showDialogForDate() {
//        DateDialog dateDialog = new DateDialog();
//        dateDialog.show(getParentFragmentManager(), "");
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}