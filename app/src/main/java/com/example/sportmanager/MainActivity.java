package com.example.sportmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private FIWStepView fiwStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ValueAnimator animator= ObjectAnimator.ofFloat(0,8000);
        animator.setInterpolator(new DecelerateInterpolator());
        fiwStepView=findViewById(R.id.step_view);
        fiwStepView.setMaxStep(10000);
        animator.setDuration(1200);
        animator.addUpdateListener((animation->{
        float  currentSteps = (float) animation.getAnimatedValue();
        fiwStepView.setCurrentStep((int) currentSteps);

        }));
        animator.start();
    }

}