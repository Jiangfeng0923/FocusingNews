package com.example.app_animate.preference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.app_animate.R;

import static com.example.app_animate.preference.ConnectStatePreference.STATE_CONNECTED;
import static com.example.app_animate.preference.ConnectStatePreference.STATE_CONNECTING;
import static com.example.app_animate.preference.ConnectStatePreference.STATE_DISCONNECTED;

public class TestPreferenceFragment extends PreferenceFragmentCompat {

    private ConnectStatePreference mPreference;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case STATE_CONNECTED:
                    mPreference.setConnectState(STATE_CONNECTED);
                    break;
                case STATE_CONNECTING:
                    mPreference.setConnectState(STATE_CONNECTING);
                    sendEmptyMessageDelayed(STATE_CONNECTED,5000);
                    break;
                case STATE_DISCONNECTED:
                    mPreference.setConnectState(STATE_DISCONNECTED);
                    break;
            }
        }
    };

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_test);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPreference = getPreferenceScreen().findPreference("connect");
        if (mPreference != null) {
            mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    int state = mPreference.getConnectState();
                    if(state==STATE_CONNECTED){
                        handler.sendEmptyMessage(STATE_DISCONNECTED);
                    }else if(state==STATE_DISCONNECTED){
                        handler.sendEmptyMessage(STATE_CONNECTING);
                    }

                    return true;
                }
            });
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
