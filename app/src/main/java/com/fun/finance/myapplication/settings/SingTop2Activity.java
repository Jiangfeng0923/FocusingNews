package com.fun.finance.myapplication.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fun.finance.myapplication.R;

public class SingTop2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("JIANG","SingTop2Activity onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("JIANG","SingTop2Activity onStop");
    }

    public void gotoother(View v){
        startActivity(new Intent("android.intent.action.singtop"));
    }
}
