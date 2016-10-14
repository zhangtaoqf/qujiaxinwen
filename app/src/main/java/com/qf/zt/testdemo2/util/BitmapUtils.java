package com.qf.zt.testdemo2.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.qf.zt.testdemo2.R;
import com.qf.zt.testdemo2.util.cache.ImageCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/5.
 */
public class BitmapUtils {
    private ExecutorService executorService;
    private static BitmapUtils bitmapUtils;
    private ImageCache imageCache;
    private BitmapUtils()
    {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    public void loadImage(final String url, final ImageView imageView)
    {
        //显示默认的图片
        imageView.setImageResource(R.drawable.default_news);

        if(imageCache!=null)
        {
            Bitmap bitmap = imageCache.getBitmap(url);
            if (bitmap!=null) {
                imageView.setImageBitmap(bitmap);
                return;
            }
        }
        //网络下载图片
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = RequestUtils.getBitmap(url);
                //加载显示图片
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("network","网络下载。。。");
                        String tag = (String) imageView.getTag();
                        if(tag.equals(url))
                        {
                            if(imageCache!=null)
                            {
                                imageCache.putBitmap(url,bitmap);
                            }
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        });
    }

    public static BitmapUtils getBitmapUtils()
    {
        if(bitmapUtils==null)
        {
            bitmapUtils = new BitmapUtils();
        }
        return bitmapUtils;
    }
}
