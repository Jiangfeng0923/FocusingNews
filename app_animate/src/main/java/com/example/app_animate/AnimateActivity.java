package com.example.app_animate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class AnimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        View view = findViewById(R.id.button);
    }


    public void doClick(View v) {
//        v.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                springAnimation_enlarge.start();
//            }
//        }, 2000);
    }
}
