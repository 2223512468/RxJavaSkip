package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

/**
 * Created by ${Terry} on 2017/9/23.
 */
public class RxConnetActivity extends Activity implements View.OnClickListener {

    TextView editV;
    TextView scanV;
    Button btnStart;
    Button unBtn;
    private Integer[] integer = {1, 2, 3, 4, 5, 6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_normal);
        editV = findViewById(R.id.edit);
        scanV = findViewById(R.id.scan);
        btnStart = findViewById(R.id.start);
        unBtn = findViewById(R.id.unbtn);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                normalStart();
                break;
        }
    }

    private void start() {

        Observable<Integer> obs = Observable.from(integer);

        Action1<Integer> obser = new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                scanV.append("A" + integer + "\n");
            }
        };

        Action1<Integer> obser1 = new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                scanV.append("B" + integer + "\n");
            }
        };

        obs.subscribe(obser);
        obs.subscribe(obser1);

    }

    private void normalStart() {

        ConnectableObservable<Integer> obs = Observable.from(integer).publish();

        Action1<Integer> obser = new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                scanV.append("A" + integer + "\n");
            }
        };

        Action1<Integer> obser1 = new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                scanV.append("B" + integer + "\n");
            }
        };

        obs.subscribe(obser);
        obs.subscribe(obser1);
        obs.connect();
    }
}
