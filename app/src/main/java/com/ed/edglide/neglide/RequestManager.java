package com.ed.edglide.neglide;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestManager {
    //单例
    private static RequestManager requestManager = null;

    //创建阻塞队列(BitmapRequest)
    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue<>();

    //创建一个线程数组(许多个银行小姐姐BitmapDispatcher)
    private BitmapDispatcher[] bitmapDispatchers;

    private RequestManager() {
        start();
    }

    public static RequestManager getInstance() {
        if (requestManager == null) {
            requestManager = new RequestManager();
        }
        return requestManager;
    }

    //将图片请求对象添加到阻塞队列中去
    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }
        //判断当前请求是否在队列中
        if (!requestQueue.contains(bitmapRequest)) {
            requestQueue.add(bitmapRequest);
        }


    }


    //开启所有线程的总开关
    public  void start(){
        stop();

        //四个小姐姐就位，共同面对一个阻塞队列（放了一排空座位new LinkedBlockingQueue<>()，等待具体来办事儿的人）
        startAllDispatcher();
    }


    //创建并开始所有的线程
    public void startAllDispatcher() {
        //获取手机支持的单个应用最大的线程数
        int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue);
            bitmapDispatcher.start();
            //要将灭一个dispatcher放到数组中，方便统一管理
            bitmapDispatchers[i] = bitmapDispatcher;
        }
    }

    //停止所有的线程
    public void stop() {
        //停止所有线程
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if(!bitmapDispatcher.isInterrupted()){
                    bitmapDispatcher.interrupt();
                }
            }
        }
    }
}
