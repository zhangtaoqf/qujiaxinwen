package com.qf.zt.testdemo2.util.cache.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qf.zt.testdemo2.util.cache.ImageCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/10/10.
 */

/**
 * 磁盘缓存
 */
public class DiskCache implements ImageCache{
    private String diskCacheDir;
    public DiskCache(String diskCacheDir) {
        this.diskCacheDir = diskCacheDir;
        File file = new File(diskCacheDir);
        if(!file.exists())
        {
            file.mkdirs();
        }
    }

    public DiskCache(Context context) {
        diskCacheDir = context.getCacheDir()+"/imgcache";
        File file = new File(diskCacheDir);
        if(!file.exists())
        {
            file.mkdirs();
        }
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        String imgPath = getDir(url);
        File file = new File(imgPath);
        if(file.exists())
            return;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(imgPath);
            bitmap.compress(Bitmap.CompressFormat.PNG,80, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getDir(String url) {
        String str = url.substring(url.lastIndexOf("/") + 1);
        return diskCacheDir+"/"+str;
    }

    @Override
    public Bitmap getBitmap(String url) {
        String imgPath = getDir(url);
        File file = new File(imgPath);
        if(!file.exists())
            return null;
        return BitmapFactory.decodeFile(imgPath);
    }
}
