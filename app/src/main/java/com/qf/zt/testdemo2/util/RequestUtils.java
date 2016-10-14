package com.qf.zt.testdemo2.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/5.
 */
public class RequestUtils {
    /**
     * get请求数据
     * @param url
     * @return
     */
    public static String get(String url)
    {
        InputStream inputStream = getInputStream(url);
        if(inputStream != null){
            byte[] bs = new byte[10 * 1024];
            int len =-1;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                while(-1!=(len =inputStream.read(bs)))
                {
                    byteArrayOutputStream.write(bs,0,len);
                }
                return byteArrayOutputStream.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 获取输入流
     * @param url
     * @return
     */
    private static InputStream getInputStream(String url) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                return httpURLConnection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 请求图片
     * @param url
     * @return
     */
    public static Bitmap getBitmap(String url)
    {
        InputStream inputStream = getInputStream(url);
        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream);
        }
        return null;
    }

}
