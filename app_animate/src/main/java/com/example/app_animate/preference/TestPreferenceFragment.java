package com.example.app_animate.preference;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.app_animate.R;

import java.util.Random;

public class TestPreferenceFragment extends PreferenceFragmentCompat {

    int lastState;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_test);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConnectStatePreference preference = getPreferenceScreen().findPreference("connect");
        if (preference != null) {
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    ConnectStatePreference connectStatePreference = (ConnectStatePreference) preference;
                    Random random = new Random();
                    int state = random.nextInt(3);
                    while (state == lastState) {
                        state = random.nextInt(3);
                    }
                    connectStatePreference.setConnectState(state);
                    lastState = state;
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
