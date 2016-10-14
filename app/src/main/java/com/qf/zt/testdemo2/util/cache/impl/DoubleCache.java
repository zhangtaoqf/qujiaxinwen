package com.qf.zt.testdemo2.util.cache.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.qf.zt.testdemo2.util.cache.ImageCache;

/**
 * Created by Administrator on 2016/10/10.
 * 双缓存：内存缓存 + 磁盘缓存
 */

public class DoubleCache implements ImageCache {
    private ImageCache discCache;
    private ImageCache memoryCache;

    public DoubleCache(int memorySize,String cacheDir) {
        this.discCache = new DiskCache(cacheDir);
        this.memoryCache = new MemoryCache(memorySize);
    }

    public DoubleCache(Context context) {
        this.discCache = new DiskCache(context);
        this.memoryCache = new MemoryCache(10<<20);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        memoryCache.putBitmap(url,bitmap);
        discCache.putBitmap(url,bitmap);
    }
    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = memoryCache.getBitmap(url);
        if(bitmap!=null){
            return bitmap;
        }
        else {
            bitmap = discCache.getBitmap(url);
            if(bitmap!=null)
            {
                memoryCache.putBitmap(url,bitmap);
            }
        }
        return bitmap;
    }
}
