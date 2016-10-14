package com.qf.zt.testdemo2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.qf.zt.testdemo2.R;
import com.qf.zt.testdemo2.fragment.BaseFragment;
import com.qf.zt.testdemo2.fragment.CommentsFragment;
import com.qf.zt.testdemo2.fragment.NewsFragment;
import com.qf.zt.testdemo2.fragment.PicsFragment;
import com.qf.zt.testdemo2.fragment.ReviewFragment;
import com.qf.zt.testdemo2.util.TabFragmentUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<BaseFragment> fragments;
    RadioGroup radioGroup;
    TabFragmentUtils tabFragmentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DbUtils dbUtils = DbUtils.getDbUtils();
//        NewsPagerEntity object = new NewsPagerEntity();
//        object.setId(10);
//        object.setEditor("haha");
//        object.setTitle("今天天气真好");
//        dbUtils.save(object);

        radioGroup = ((RadioGroup) findViewById(R.id.activity_main_radioGroupId));
        fragments = new ArrayList<>();
        fragments.add(NewsFragment.newInstance());
        fragments.add(ReviewFragment.newInstance());
        fragments.add(PicsFragment.newInstance());
        fragments.add(CommentsFragment.newInstance());
        //实现切换fragment的功能
        /**
         * 参数一：radiogroup
         * 参数二：fragment的页面
         * 参数三：占位布局id
         * 参数四：fragmentManger,帮助去切换fragment
         *
         */
        tabFragmentUtils = new TabFragmentUtils(radioGroup, fragments, R.id.activity_main_frameLayoutId,getSupportFragmentManager());
    }
    private int backStep = 0;
    @Override
    public void onBackPressed() {
        if(backStep == 0)
        {
            new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    backStep = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        }
        if(backStep == 1)
        {
            super.onBackPressed();
        }
        else
        {
            backStep++;
        }
    }
}
