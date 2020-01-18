package com.example.app_animate.preference;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.example.app_animate.R;

public class ConnectStatePreference extends Preference {
    public static final int STATE_ENABLED = 0;
    public static final int STATE_CONNECTED = 1;
    public static final int STATE_CONNECTING = 2;
    private static final int STATE_DISABLED = 3;


    int[] CONNECTED_STATE = new int[]{android.R.attr.state_selected};
    int[] DISCONNECTED_STATE = new int[]{android.R.attr.state_enabled};

    private int mState = STATE_CONNECTED;
    private AnimatedVectorDrawable mBackground;
    private View mParentView;
    private ImageView imageView ;

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
        mParentView = holder.itemView;
        imageView = (ImageView) holder.findViewById(android.R.id.icon);
        updateState();
    }

    private void updateState() {

        if (mParentView == null) {
            return;
        }
        switch (mState) {
            case STATE_CONNECTING:
                AnimatedVectorDrawable drawableCompat = (AnimatedVectorDrawable) ContextCompat.getDrawable(getContext(), R.drawable.connecting_animate);
                if (drawableCompat != null) {
                    drawableCompat.start();
                    mParentView.setBackground(drawableCompat);
                }
//                getIcon().setState(DISCONNECTED_STATE);
//                imageView.setEnabled(false);
                imageView.setImageState(DISCONNECTED_STATE,true);
                break;
            case STATE_ENABLED:
//                getIcon().setState(DISCONNECTED_STATE);
//                imageView.setEnabled(false);
                imageView.setImageState(DISCONNECTED_STATE,true);
                mParentView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ic_brightness_1_black_24dp));
                break;
            case STATE_CONNECTED:
                imageView.setImageState(CONNECTED_STATE,true);
                mParentView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ic_brightness_1_blue_24dp));
//                getIcon().setState(CONNECTED_STATE);
//                imageView.setEnabled(true);
                break;

        }


    }
}
