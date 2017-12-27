package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by ${Terry} on 2017/9/23.
 */
public class RxTimerActivity extends Activity implements View.OnClickListener {

    TextView editV;
    TextView scanV;
    Button btnStart;
    Button unBtn;
    Subscription subscription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_normal);
        editV = findViewById(R.id.edit);
        scanV = findViewById(R.id.scan);
        btnStart = findViewById(R.id.start);
        unBtn = findViewById(R.id.unbtn);
        editV.setText("定时器，每一秒发送打印一个数字   \n\ninterval(1, TimeUnit.SECONDS)  创建一个每隔一秒发送一次事件的对象");
        btnStart.setOnClickListener(this);
        unBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                start();
                break;
            case R.id.unbtn:
                if (subscription != null && !subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
                break;
        }
    }

    private void start() {

        subscription = Observable.interval(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        scanV.append(aLong + "\n");
                    }
                });
    }
}
