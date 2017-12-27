package com.rxjavaskip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_01).setOnClickListener(this);
        findViewById(R.id.btn_02).setOnClickListener(this);
        findViewById(R.id.btn_03).setOnClickListener(this);
        findViewById(R.id.btn_04).setOnClickListener(this);
        findViewById(R.id.btn_05).setOnClickListener(this);
        findViewById(R.id.btn_06).setOnClickListener(this);
        findViewById(R.id.btn_07).setOnClickListener(this);
        findViewById(R.id.btn_08).setOnClickListener(this);
        findViewById(R.id.btn_09).setOnClickListener(this);
        findViewById(R.id.btn_10).setOnClickListener(this);
        findViewById(R.id.btn_11).setOnClickListener(this);
        findViewById(R.id.btn_12).setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_01:
                intent = new Intent(this, NormalRxActivity.class);
                break;
            case R.id.btn_02:
                intent = new Intent(this, RxMapActivity.class);
                break;
            case R.id.btn_03:
                intent = new Intent(this, RxSchuderActivity.class);
                break;
            case R.id.btn_04:
                intent = new Intent(this, RxFlatMapActivity.class);
                break;
            case R.id.btn_05:
                intent = new Intent(this, RxMergeActivity.class);
                break;
            case R.id.btn_06:
                intent = new Intent(this, RxBindingActivity.class);
                break;
            case R.id.btn_07:
                intent = new Intent(this, RxFilterActivity.class);
                break;
            case R.id.btn_08:
                intent = new Intent(this, RxTakeActivity.class);
                break;
            case R.id.btn_09:
                intent = new Intent(this, RxTimerActivity.class);
                break;
            case R.id.btn_10:
                intent = new Intent(this, RxSortActivity.class);
                break;
            case R.id.btn_11:
                intent = new Intent(this, RxConnetActivity.class);
                break;
            case R.id.btn_12:
                intent = new Intent(this, TimestampActivity.class);
                break;
        }

        startActivity(intent);
    }
}
