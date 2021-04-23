package com.example.mypillsproject.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mypillsproject.repo.MainRepo;

import java.util.List;

public class MainViewModel extends AndroidViewModel {


    private MainRepo mainRepo;

    private LiveData<List<PillsItem>> pillsItem;


    public MainViewModel(@NonNull Application application) {
        super(application);
        mainRepo = new MainRepo(application.getApplicationContext());
    }

    public LiveData<List<PillsItem>> getPills() {
        if (pillsItem == null) {
            pillsItem = mainRepo.getItemFromDatabase();
        }
        return pillsItem;
    }

    public void deletePill(PillsItem item) {
        mainRepo.deletePill(item);

    }

    public void updatePill(PillsItem item) {
        mainRepo.updatePill(item);

    }
}
