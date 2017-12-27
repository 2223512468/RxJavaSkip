package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ${Terry} on 2017/9/21.
 */
public class RxBindingActivity extends Activity {

    private TextView mText;
    private Button mBtn;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3);
        mText = (TextView) findViewById(R.id.text1);
        mEdit = (EditText) findViewById(R.id.edit1);
        mBtn = (Button) findViewById(R.id.button);
        mEdit.setHint("输入含有1的数字，下方才会出现提示");
        mText.setText("提示数据：\n");
        start();

    }


    private void start() {

        RxTextView.textChanges(mEdit)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<CharSequence, List<String>>() {
                    List<String> mList = new ArrayList<String>();

                    @Override
                    public List<String> call(CharSequence c) {
                        if (c.toString().contains("1")) {
                            for (int i = 0; i < 5; i++) {
                                mList.add("11" + i);
                            }
                        }
                        return mList;
                    }
                }).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !mText.getText().toString().contains(s);

                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mText.append(s + "\n");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }
}
