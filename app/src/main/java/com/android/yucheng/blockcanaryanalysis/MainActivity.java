package com.android.yucheng.blockcanaryanalysis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View view) {
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "卡顿", Toast.LENGTH_SHORT).show();
    }

    public void test2(View view) {
        Toast.makeText(this, "不卡顿", Toast.LENGTH_SHORT).show();
    }
}
