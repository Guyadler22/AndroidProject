package com.example.mypillsproject;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.mypillsproject.ui.fragments.mainFragment.HomeFragment;
import com.example.mypillsproject.ui.fragments.mainFragment.PillsFragment;
import com.example.mypillsproject.ui.fragments.mainFragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);
        setUpNavigation();

    }

    public void setUpNavigation() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
        NavController navController = Navigation.findNavController(this, R.id.fragment);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment selectedItem = null;
                switch (item.getItemId()) {
                    case R.id.homeFragment:
                        selectedItem = new HomeFragment();
                        break;
                    case R.id.PillsFragment:
                        selectedItem = new PillsFragment();
                        break;
                    case R.id.settingsFragment2:
                        selectedItem = new SettingFragment();
                        break;
                }
            }
        });

        //this function disappear the bottomNavigation on login fragment.
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.loginFragment) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}


