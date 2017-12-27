package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Timestamped;

/**
 * Created by ${Terry} on 2017/9/23.
 */
public class TimestampActivity extends Activity implements View.OnClickListener {

    TextView editV;
    TextView scanV;
    Button btnStart;
    Button unBtn;
    private Integer[] words = {1, 3, 5, 2, 34, 7, 5, 86, 23, 43};

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                start();
                break;
        }
    }


    private void start() {

        Observable.from(words)
                .timestamp()
                .subscribe(new Action1<Timestamped<Integer>>() {
                    @Override
                    public void call(Timestamped<Integer> integerTimestamped) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:SS");
                        scanV.append("time" + integerTimestamped.getValue());
                        scanV.append(sdf.format(new Date(integerTimestamped.getTimestampMillis())) + "\n");
                    }
                });
    }
}
