package com.fun.finance.myapplication.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fun.finance.myapplication.R;

import static com.fun.finance.myapplication.settings.Constant.BAIDU;
import static com.fun.finance.myapplication.settings.Constant._360;
import static com.fun.finance.myapplication.settings.Constant.KEY_ENGINE;
import static com.fun.finance.myapplication.settings.Constant.SOGOU;

public class SearchEnginesSettingsActivity extends AppCompatActivity {
    int sogou_id = R.id.sogou_btn;
    int _360_id = R.id._360_btn;
    int baidu_btn = R.id.baidu_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_engines_settings);
        RadioGroup group = findViewById(R.id.search_engines);
        RadioButton sogou = findViewById(sogou_id);
        RadioButton btn_360 = findViewById(_360_id);
        RadioButton btn_baidu = findViewById(baidu_btn);

        SharedPreferences sp = getSharedPreferences("search_engines", Context.MODE_PRIVATE);
        String engine = sp.getString(KEY_ENGINE, SOGOU);
        if (TextUtils.equals(engine, _360)) {
            btn_360.setChecked(true);
        } else if (TextUtils.equals(engine, SOGOU)) {
            sogou.setChecked(true);
        } else if (TextUtils.equals(engine, BAIDU)) {
            btn_baidu.setChecked(true);
        }

        final SharedPreferences.Editor editor = sp.edit();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == sogou_id) {
                    editor.putString(KEY_ENGINE, SOGOU);
                } else if (checkedId == _360_id) {
                    editor.putString(KEY_ENGINE, _360);
                } else if (checkedId == baidu_btn) {
                    editor.putString(KEY_ENGINE, BAIDU);
                }
                editor.commit();
            }
        });
    }
}
