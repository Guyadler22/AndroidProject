package com.example.mypillsproject.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mypillsproject.db.ItemDao;
import com.example.mypillsproject.db.RoomDb;
import com.example.mypillsproject.viewModels.PillsItem;

import java.util.List;

public class MainRepo {

    private ItemDao dao;

    public MainRepo(Context context) {
        RoomDb db = RoomDb.getInstance(context);
        dao = db.itemDao();
    }

    public LiveData<List<PillsItem>> getItemFromDatabase() {
        return dao.getAllPillsItem();
    }

    public void insertPill(PillsItem item) {
        new insertPillTask(dao, item).start();
    }

    public void updatePill(PillsItem item) {
        new updatePillTask(dao, item).start();
    }

    public void deletePill(PillsItem item) {
        new deletePilTask(dao, item).start();
    }


    private static class insertPillTask extends Thread {
        PillsItem item;
        ItemDao dao;

        public insertPillTask(ItemDao dao, PillsItem item) {
            this.item = item;
            this.dao = dao;
        }

        @Override
        public void run() {
            super.run();
            dao.InsertPillsItem(item);
        }
    }

    private static class updatePillTask extends Thread {
        PillsItem item;
        ItemDao dao;

        public updatePillTask(ItemDao dao, PillsItem item) {
            this.item = item;
            this.dao = dao;
        }

        @Override
        public void run() {
            super.run();
            dao.updateItem(item);
        }
    }

    private static class deletePilTask extends Thread {
        PillsItem item;
        ItemDao dao;

        public deletePilTask(ItemDao dao, PillsItem item) {
            this.item = item;
            this.dao = dao;
        }

        @Override
        public void run() {
            super.run();
            dao.deleteItem(item);
        }
    }

}

