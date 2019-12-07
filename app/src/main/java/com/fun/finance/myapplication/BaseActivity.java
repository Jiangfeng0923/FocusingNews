package com.fun.finance.myapplication;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BaseActivity extends AppCompatActivity {

    public class TestObj{
        int value;

        public TestObj(int value) {
            this.value=value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {

            return value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button button = findViewById(R.id.button);
//        AnimatorInflater.loadAnimator()
        final ValueAnimator animator = ValueAnimator.ofInt(0,1,2,3,4,5,6,7,8,9,10);
        animator.setDuration(5000);
//        animator.setRepeatCount(5);
//        animator.setRepeatMode(REVERSE);
//        animator.setInterpolator(new AccelerateInterpolator());
        animator.setInterpolator(new DecelerateInterpolator());
        final TestObj obj = new TestObj(0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int)animation.getAnimatedValue();
//                obj.setValue(value);
                button.setText(String.valueOf(value));
            }
        });

        final ValueAnimator animator2 = ValueAnimator.ofFloat(0.0f,0.2f,0.4f,0.6f,0.8f,1.0f);
        animator2.setInterpolator(new AccelerateInterpolator());
        animator2.setDuration(5000);

        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float)animation.getAnimatedValue();
//                obj.setValue(value);
                button.setAlpha(value);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                animator.start();
                animator2.start();
            }
        });
    }

}
