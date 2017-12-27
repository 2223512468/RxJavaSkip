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
 * Created by ${Terry} on 2017/9/20.
 */
public class RxMapActivity extends Activity implements View.OnClickListener {

    TextView editV;
    TextView scanV;
    Button btnStart;
    private Integer[] numbers = {1, 2, 3, 4, 5, 6};

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
        editV.setText("输入Integer(1,2,3,4,5,6) 是>3&&<3\n输出true or false");
        btnStart.setText("开始");

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

        Observable.from(numbers)
                .map(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 3;
                    }
                })
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        scanV.append("输出观察结果");
                        scanV.append(aBoolean + "\n");
                    }
                });
    }
}
