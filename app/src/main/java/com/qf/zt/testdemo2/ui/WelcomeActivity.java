package com.qf.zt.testdemo2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qf.zt.testdemo2.R;
import com.qf.zt.testdemo2.util.BitmapUtils;
import com.qf.zt.testdemo2.util.cache.impl.DoubleCache;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置图片缓存
        BitmapUtils.getBitmapUtils().setImageCache(new DoubleCache(this));
        ImageView view = new ImageView(this);
        view.setImageResource(R.drawable.start);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        setContentView(view);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    WelcomeActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity( new Intent(WelcomeActivity.this,MainActivity.class));
                            //overridePendingTransition();
                            finish();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
