package com.fun.finance.myapplication.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fun.finance.myapplication.R;

public class SingTopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_top);
    }

    public void gotoother(View v){
        startActivity(new Intent("android.intent.action.singtop2"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("JIANG","SingTopActivity onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("JIANG","SingTopActivity onNewIntent");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("JIANG","SingTopActivity onStop");
    }

}
