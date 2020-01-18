package com.example.app_animate.preference;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.example.app_animate.R;

public class ConnectStatePreference extends Preference {
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_CONNECTED = 1;
    public static final int STATE_CONNECTING = 2;
    private static final int STATE_DISABLED = 3;


    int[] CONNECTED_STATE = new int[]{R.attr.state_connected};
    int[] DISCONNECTED_STATE = new int[]{-R.attr.state_connected};

    private int mState = STATE_DISCONNECTED;
    private AnimatedVectorDrawable mBackground;
    private View mParentView;
    private ImageView imageView;

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


    public int getConnectState() {
        return mState;
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

                imageView.setImageState(DISCONNECTED_STATE, true);
                break;
            case STATE_DISCONNECTED:

                imageView.setImageState(DISCONNECTED_STATE, true);
                mParentView.setBackground(ContextCompat.getDrawable(getContext(), android.R.color.darker_gray));
                break;
            case STATE_CONNECTED:
                imageView.setImageState(CONNECTED_STATE, true);
                mParentView.setBackground(ContextCompat.getDrawable(getContext(), android.R.color.holo_blue_light));

                break;

        }


    }
}
