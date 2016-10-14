package com.qf.zt.testdemo2.util.cache.impl;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.qf.zt.testdemo2.util.cache.ImageCache;

/**
 * Created by Administrator on 2016/10/5.
 * 内存缓存
 */
public class MemoryCache implements ImageCache {
    private LruCache<String,Bitmap> lruCache;

    public MemoryCache(int memorySize) {
        this.lruCache = new LruCache<String,Bitmap>(memorySize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Bitmap bitmap1 = lruCache.get(url);
        if(bitmap1 == bitmap)
            return ;
        Log.i("info","存图片...");
        lruCache.put(url,bitmap);
    }

    @Override
    public Bitmap getBitmap(String url) {
        Log.i("info","取图片...");
        return lruCache.get(url);
    }

}
