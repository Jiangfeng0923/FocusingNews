package com.example.dialogactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.miuix.AlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickBtn(View view){

        final TypedArray a = obtainStyledAttributes(null, R.styleable.testCheckWidgetDrawable,
                R.attr.test_attr, 0);

        int mAlertDialogLayout = a.getResourceId(R.styleable.testCheckWidgetDrawable_testlayout, -1);

        a.recycle();

        Log.d("JIANG","mAlertDialogLayout:"+mAlertDialogLayout);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_LIGHT);
        builder.setTitle("测试");
        builder.setMessage("消息");
        builder.show();

    }
}
