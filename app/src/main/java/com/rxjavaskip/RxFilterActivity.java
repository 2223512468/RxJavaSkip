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
 * Created by ${Terry} on 2017/9/21.
 */
public class RxFilterActivity extends Activity implements View.OnClickListener {

    private TextView mText;
    private Button mBtn;
    private TextView mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_normal);
        mText = (TextView) findViewById(R.id.edit);
        mEdit = (TextView) findViewById(R.id.scan);
        mBtn = (Button) findViewById(R.id.start);

        mBtn.setOnClickListener(this);
        mText.setOnClickListener(this);
        mEdit.setOnClickListener(this);
        mEdit.setText("输入1-10,过滤掉能被2整除的数");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text1:
                break;
            case R.id.edit1:
                break;
            case R.id.start:

                start();
                break;
        }
    }

    private void start() {

        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Observable.from(numbers).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 != 0;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                mText.append(integer + "");
            }
        });
    }
}
