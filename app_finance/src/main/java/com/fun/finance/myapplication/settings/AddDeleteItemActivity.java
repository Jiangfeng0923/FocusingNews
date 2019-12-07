package com.fun.finance.myapplication.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fun.finance.myapplication.R;

public class AddDeleteItemActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_add);
        SharedPreferences sp = getSharedPreferences("markets", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void doAddItem(View v) {
        EditText editText = (EditText) findViewById(R.id.add_item);
        editor.putString(editText.getText().toString(), "");
        editor.commit();
    }

    public void doDeleteItem(View v) {
        EditText editText = (EditText) findViewById(R.id.add_item);
        editor.remove(editText.getText().toString());
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("JIANG", "AddDeleteItemActivity onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("JIANG", "AddDeleteItemActivity onStop");
    }

}
