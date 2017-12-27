package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2017/9/21.
 */
public class RxMergeActivity extends Activity implements View.OnClickListener {

    private Button btnStart;
    private TextView scanV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_normal);
        btnStart = findViewById(R.id.start);
        scanV = findViewById(R.id.scan);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                start();
                break;
        }
    }


    private void start() {

        Observable<String> obs1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(500);
                    subscriber.onNext("aaa");
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> obs2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    Thread.sleep(1000);
                    subscriber.onNext("bbb");
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable.merge(obs1, obs2).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {

            StringBuffer sb = new StringBuffer();

            @Override
            public void onCompleted() {
                scanV.append("数据更新完毕。。。\n");
                scanV.append("总共得到数据" + sb.toString() + "\n");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                sb.append(s + ",");
                scanV.append("得到一数据" + s + "\n");
            }
        });
    }
}
