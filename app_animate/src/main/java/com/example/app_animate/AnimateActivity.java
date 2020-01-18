package com.example.app_animate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Hashtable;

public class AnimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        View view = findViewById(R.id.button);
    }


    public void doClick(View v) {
    }
}
