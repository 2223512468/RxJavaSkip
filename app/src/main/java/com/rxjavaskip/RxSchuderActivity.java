package com.rxjavaskip;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2017/9/21.
 */
public class RxSchuderActivity extends Activity implements View.OnClickListener {

    private LinearLayout ll;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_normal);
        ll = findViewById(R.id.linearlayout);
        btnStart = findViewById(R.id.start);
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

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable resId = getResources().getDrawable(R.mipmap.ic_dog);
                subscriber.onNext(resId);
                subscriber.onCompleted();
            }
        }).map(new Func1<Drawable, ImageView>() {
            @Override
            public ImageView call(Drawable drawable) {
                ImageView img = new ImageView(RxSchuderActivity.this);
                img.setImageDrawable(drawable);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                img.setLayoutParams(params);
                return img;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ImageView>() {
                    @Override
                    public void call(ImageView imageView) {
                        ll.addView(imageView);
                    }
                });
    }
}
