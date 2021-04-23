package com.example.mypillsproject.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mypillsproject.repo.MainRepo;

public class HomeViewModel extends AndroidViewModel {
    MainRepo mainRepo;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mainRepo = new MainRepo(application.getApplicationContext());
    }

    public void updatePill(PillsItem item) {
        mainRepo.updatePill(item);
    }

    public void insertPill(PillsItem item) {
        mainRepo.insertPill(item);
    }
}
