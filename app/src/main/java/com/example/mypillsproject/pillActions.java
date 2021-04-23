package com.example.mypillsproject;

import com.example.mypillsproject.viewModels.PillsItem;

public interface pillActions {
    void deletePill(PillsItem pill);
    void updatePill(PillsItem pill);
}