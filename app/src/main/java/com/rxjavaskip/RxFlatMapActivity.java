package com.rxjavaskip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Terry} on 2017/9/21.
 */
public class RxFlatMapActivity extends Activity implements View.OnClickListener {


    private Button btnStart;
    private TextView scanV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_normal);
        btnStart = findViewById(R.id.start);
        scanV = findViewById(R.id.scan);
        btnStart.setOnClickListener(this);
        initData();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start:
                start();
                break;
        }
    }

    private List<SchoolClass> schooList = new ArrayList<>();

    public List<SchoolClass> getSchooList() {
        return schooList;
    }

    private void initData() {

        List<Student> mList1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student1 = new Student();
            student1.setName("狗蛋" + i);
            student1.setAge(17 + i);
            mList1.add(student1);
        }
        SchoolClass schoolClass = new SchoolClass(mList1);

        List<Student> mList2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student2 = new Student();
            student2.setName("狗蛋" + 10 + i);
            student2.setAge(19 + i);
            mList2.add(student2);
        }
        SchoolClass schoolClass1 = new SchoolClass(mList2);

        schooList.add(schoolClass);
        schooList.add(schoolClass1);
    }


    private void start() {

        Observable.from(getSchooList()).flatMap(new Func1<SchoolClass, Observable<Student>>() {
            @Override
            public Observable<Student> call(SchoolClass schoolClass) {
                return Observable.from(schoolClass.getStudents());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        scanV.append(student.getName() + "--" + student.getAge() + "\n");
                    }
                });
    }


    public class SchoolClass {

        private List<Student> students;

        public SchoolClass(List<Student> students) {
            this.students = students;
        }

        public List<Student> getStudents() {
            return students;
        }

    }

    public class Student {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
