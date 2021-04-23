package com.example.mypillsproject.ui.fragments.mainFragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mypillsproject.R;

public class appGuide extends Fragment {

    LottieAnimationView lottieAnimationView;
    TextView textView;

    public appGuide() {
        super(R.layout.app_guide);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.explainTV);
        lottieAnimationView = view.findViewById(R.id.guidelineAnim);
        lottieAnimationView.setSpeed(1);
        lottieAnimationView.playAnimation();
        // wait until the animation finish
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.setProgress(0);
                Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        }, 5000);

//        Navigation.findNavController().navigate(R.id.loginFragment);
    }
}
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.app_guide, container, false);
//        return view;
//    }

