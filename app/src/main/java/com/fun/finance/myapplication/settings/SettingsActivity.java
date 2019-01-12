package com.fun.finance.myapplication.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.fun.finance.myapplication.R;

import java.util.Map;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sp;
    EditText editText1;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sp = getSharedPreferences("markets", Context.MODE_PRIVATE);
        editText1 = findViewById(R.id.editText1);
        editText1.setText(sp.getString("0", "石油"));
        editText2 = findViewById(R.id.editText2);
        editText2.setText(sp.getString("1", "黄金"));
        editText3 = findViewById(R.id.editText3);
        editText3.setText(sp.getString("2", "大豆"));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("JIANG", "SettingsActivity onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("JIANG", "SettingsActivity onNewIntent");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("JIANG", "SettingsActivity onStop");
    }

    public void doCommit(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("0", editText1.getText().toString());
        editor.putString("1", editText2.getText().toString());
        editor.putString("2", editText3.getText().toString());
        editor.commit();
    }

}
