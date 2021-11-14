package com.example.sportmanager;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;



public class StepFragment extends Fragment {

    private FIWStepView fiwStepView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_blank, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        ValueAnimator animator= ObjectAnimator.ofFloat(0,8000);
        animator.setInterpolator(new DecelerateInterpolator());
        fiwStepView= getView().findViewById(R.id.step_view);
        fiwStepView.setMaxStep(10000);
        animator.setDuration(1200);
        animator.addUpdateListener((animation->{
            float  currentSteps = (float) animation.getAnimatedValue();
            fiwStepView.setCurrentStep((int) currentSteps);

        }));
        animator.start();
    }
}