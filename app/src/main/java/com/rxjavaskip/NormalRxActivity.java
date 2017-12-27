package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ${Terry} on 2017/9/20.
 */
public class NormalRxActivity extends Activity implements View.OnClickListener {

    TextView editV;
    TextView scanV;
    Button btnStart;

    private String desc = "一二三四五\n上山打老虎\n老虎一发威\n武松就发怵";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_normal);
        editV = findViewById(R.id.edit);
        scanV = findViewById(R.id.scan);
        btnStart = findViewById(R.id.start);
        editV.setOnClickListener(this);
        scanV.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        editV.setText(desc);

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

        Observable observable = createObservable();

        Subscriber subscriber = createSubscriber();

        observable.subscribe(subscriber);

    }

    private Observable<String> createObservable() {
        //创建被观察者
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("一二三四五");
                subscriber.onNext("上山打老虎");
                subscriber.onNext("老虎一发威");
                subscriber.onNext("武松就发怵");
                subscriber.onCompleted();
            }
        });

    }

    private Subscriber<String> createSubscriber() {
        //创建观察者
        return new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
                scanV.append("观察者开始接收并执行。。\n");
            }

            @Override
            public void onCompleted() {
                scanV.append("观察者并执行完毕。。\n");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                scanV.append(s + "\n");
            }
        };
    }

}
