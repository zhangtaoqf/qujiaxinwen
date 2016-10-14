package com.qf.zt.testdemo2.util;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/5.
 */
public class HttpUtils {

    private ExecutorService executorService;
    private static HttpUtils httpUtils;

    private HttpUtils(){
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        handler = new Handler();
    }

    private HttpUtils(int threadPool){
        executorService = Executors.newFixedThreadPool(threadPool);
        handler = new Handler();
    }
    private Handler handler;
    public void sendRequest(final String url, final Listener listener){
        //开启子线程执行网络任务
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                final String s = RequestUtils.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResponse(s);
                    }
                });
            }
        });
    }

    public interface Listener{
        public void onResponse(String result);
    }

    public static HttpUtils getInstance(int threadPool)
    {
        if(httpUtils == null)
        {
            httpUtils = new HttpUtils(threadPool);
        }
        return httpUtils;
    }
    public static HttpUtils getInstance()
    {
        if(httpUtils == null)
        {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

}
