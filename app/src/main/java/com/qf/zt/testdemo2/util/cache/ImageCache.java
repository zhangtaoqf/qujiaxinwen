package com.qf.zt.testdemo2.util.cache;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/10/5.
 */
public interface ImageCache {
    /**
     * 存图片
     * @param url
     * @param bitmap
     */
    public void putBitmap(String url,Bitmap bitmap);

    /**
     *
     * 取图片
     * @param url
     * @return
     */
    public Bitmap getBitmap(String url);
}
