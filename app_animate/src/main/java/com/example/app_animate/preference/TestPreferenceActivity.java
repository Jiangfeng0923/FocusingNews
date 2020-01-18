package com.example.app_animate.preference;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_animate.R;

public class TestPreferenceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragnent_par, new TestPreferenceFragment()).commit();
    }
}
