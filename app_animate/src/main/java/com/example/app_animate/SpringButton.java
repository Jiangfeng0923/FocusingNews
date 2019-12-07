package com.example.app_animate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class SpringButton extends AppCompatButton {


    SpringAnimation springAnimation;
    SpringAnimation springAnimation_enlarge;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(springAnimation.isRunning()){
                    springAnimation.cancel();
                }
                springAnimation.start();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if(springAnimation_enlarge.isRunning()){
                    springAnimation_enlarge.cancel();
                }
                springAnimation_enlarge.start();
        }
        return true;
    }

    public SpringButton(Context context) {
        this(context, null);
    }

    public SpringButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        springAnimation = new SpringAnimation(this, SpringAnimation.SCALE_X, 0.5f);
        springAnimation.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
        springAnimation.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);

        springAnimation_enlarge = new SpringAnimation(this, SpringAnimation.SCALE_X, 1.0f);
        springAnimation_enlarge.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
        springAnimation_enlarge.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnimation_enlarge.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                Log.d("JIANG", "value:" + value + " veloc:" + velocity);
            }
        });
    }

}
