package com.example.app_animate.preference;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.example.app_animate.R;

public class ConnectStatePreference extends Preference {
    private static int STATE_ENABLED = 0;
    private static int STATE_CONNECTED = 1;
    private static int STATE_CONNECTING = 2;
    private static int STATE_DISABLED = 3;
    private int mState;
    private AnimatedVectorDrawable mBackground;

    public ConnectStatePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setLayoutResource(R.layout.connect_state_preference);
    }

    public ConnectStatePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ConnectStatePreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConnectStatePreference(Context context) {
        this(context, null);
    }

    public void setConnectState(int state) {
        mState = state;
        updateState();
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View parent = holder.itemView;
        // View parent = holder.findViewById(R.id.preference_parent);

        AnimatedVectorDrawable drawableCompat= (AnimatedVectorDrawable)ContextCompat.getDrawable(getContext(),R.drawable.connecting_animate);
        parent.setBackground(drawableCompat);
        mBackground = (AnimatedVectorDrawable) parent.getBackground();
        mBackground.start();


    }

    private void updateState() {

    }
}
