package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ${Terry} on 2017/9/23.
 */
public class RxTakeActivity extends Activity implements View.OnClickListener {

    TextView editV;
    TextView scanV;
    Button btnStart;
    private Integer[] number = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


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
        editV.setText("输出[1,2,3,4,5,6,7,8,9,10]中第三个和第四个奇数，\n\ntake(i) 取前i个事件 \ntakeLast(i) 取后i个事件 \ndoOnNext(Action1) 每次观察者中的onNext调用之前调用");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                start();
                break;
        }
    }

    private void start() {

        Observable.from(number).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 != 0;
            }
        }).take(4)
                .takeLast(2)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        scanV.append("之前\n");
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                scanV.append(integer + "\n");
            }
        });
    }
}
