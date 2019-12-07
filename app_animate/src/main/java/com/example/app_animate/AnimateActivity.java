package com.example.app_animate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.os.Bundle;
import android.view.View;

public class AnimateActivity extends AppCompatActivity {
    SpringAnimation springAnimation;
    SpringAnimation springAnimation_enlarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        View view = findViewById(R.id.button);
        springAnimation = new SpringAnimation(view, SpringAnimation.SCALE_X, 0.3f);
        springAnimation.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springAnimation.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        springAnimation_enlarge = new SpringAnimation(view, SpringAnimation.SCALE_X, 1.4f);
        springAnimation_enlarge.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springAnimation_enlarge.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
    }

    public void doClick(View v) {
        springAnimation.start();
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                springAnimation_enlarge.start();
            }
        }, 1000);
    }
}
