package com.example.mypillsproject.ui.fragments.mainFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypillsproject.R;
import com.example.mypillsproject.RecyclerView.RecyclerViewPills;
import com.example.mypillsproject.pillActions;
import com.example.mypillsproject.utils.extesions.Utils;
import com.example.mypillsproject.viewModels.MainViewModel;
import com.example.mypillsproject.viewModels.PillsItem;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PillsFragment extends Fragment implements pillActions {

    private RecyclerViewPills pillsRecyclerAdapter;
    RecyclerView recyclerView;
    ArrayList<PillsItem> pillsItemList;
    MainViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageView deleteIcon = view.findViewById(R.id.ic_delete);
        // set up the RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Get the ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //Create the observer witch update the UI
        pillsItemList = new ArrayList<>();
        pillsRecyclerAdapter = new RecyclerViewPills(this);
        recyclerView.setAdapter(pillsRecyclerAdapter);

        viewModel.getPills().observe(getViewLifecycleOwner(), new Observer<List<PillsItem>>() {
            @Override
            public void onChanged(List<PillsItem> pillsItems) {
                pillsRecyclerAdapter.setPillsItem(pillsItems);
            }
        });
        pillsItemList = new ArrayList<>();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        System.err.println("Reached Fragment Menu");
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pillsRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pills, container, false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void deletePill(PillsItem pill) {
        viewModel.deletePill(pill);
    }

    @Override
    public void updatePill(PillsItem pill) {
        new Utils().getDateTimeDialog(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                pill.setTimePicker(date.getTime());
                viewModel.updatePill(pill);
            }

            @Override
            public void onNegativeButtonClick(Date date) {
            }
        }).show(getChildFragmentManager(), "Pill_Date_Dialog");
    }
}