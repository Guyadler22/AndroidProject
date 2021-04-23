package com.example.mypillsproject.ui.fragments.mainFragment.login;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mypillsproject.R;

public class startFragment extends Fragment {
    LottieAnimationView lottieAnimationView;
    boolean wasClicked = false;

    public startFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lottieAnimationView = view.findViewById(R.id.lottieAnimation);
        lottieAnimationView.setSpeed(1);
        lottieAnimationView.playAnimation();
        // wait until the animation finish
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.setProgress(0);
                signIn(view);
            }
        }, 2000);

    }

    public static void signIn(View view) {
        Navigation.findNavController(view).navigate(R.id.actionGlobal_to_homeFragment);
    }
}