package com.fun.finance.myapplication.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fun.finance.myapplication.R;

import java.util.Iterator;
import java.util.Map;

public class EditItemsActivity extends AppCompatActivity {
    SharedPreferences sp;
    Map<String, ?> mConcernedMarket;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        linearLayout = findViewById(R.id.settings_parent);
        sp = getSharedPreferences("markets", Context.MODE_PRIVATE);
        mConcernedMarket = sp.getAll();

        Iterator iterator = mConcernedMarket.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();

            EditText editText = new EditText(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(layoutParams);
            editText.setText(key);
            linearLayout.addView(editText);
        }
    }

    public void doCommit(View v) {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        Iterator iterator = mConcernedMarket.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            View child = linearLayout.getChildAt(index);
            String string = ((EditText) child).getText().toString();
            if (!TextUtils.isEmpty(string)) {
                editor.putString(string, "");
            }
            iterator.next();
            index++;
        }
        editor.commit();
    }


}
