package com.ed.edglide.neglide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

public class BitmapDispatcher extends Thread{

    //创建一个阻塞队列
    private LinkedBlockingQueue<BitmapRequest> requestQueue;

    public BitmapDispatcher (LinkedBlockingQueue linkedBlockingQueue){
        this.requestQueue = linkedBlockingQueue;
    }


    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void run() {
        super.run();
        //线程是否被干掉
        while (!isInterrupted()){
            try {
                BitmapRequest br = requestQueue.take();

                //设置占位图片
                showLoadingImg(br);

                //加载图片
                Bitmap bitmap = findBitmap(br);

                //把图片显示到ImageView
                showImageView(br,bitmap);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    private void showLoadingImg(BitmapRequest br) {
        if (br.getResID()>0&&br.getImageView()!=null){
            int resID = br.getResID();
            ImageView imageView = br.getImageView();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    //更新UI要切换线程
                    imageView.setImageResource(resID);
                }
            });
        }
    }



    private Bitmap findBitmap(BitmapRequest br) {

        Bitmap bitmap = null;
        bitmap = downloadImage(br.getUrl());

        //加入缓存机制memory+disk


        return bitmap;
    }

    private Bitmap downloadImage(String uri) {
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null;


        try {
            //创建一个URL对象
            URL url = new URL(uri);
            //使用HttpURLConnection通过URL去开始读取数据
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //HttpURLConnection获得输入流
            is = conn.getInputStream();
            //BitmapFactory转化输入流变成Bitmap
            bitmap = BitmapFactory.decodeStream(is);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;

    }



    private void showImageView(BitmapRequest br, Bitmap bitmap) {

        //避免图片错位br.getUrlMd5().equals(br.getImageView().getTag())
        if (bitmap != null&&br.getImageView()!=null
        &&br.getUrlMd5().equals(br.getImageView().getTag())) {

            ImageView imageView = br.getImageView();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);

                    //用户通过监听可以做一些其他操作
                    if (br.getRequestListener()!=null) {
                        RequestListener listener = br.getRequestListener();
                        listener.onSuccess(bitmap);
                    }
                }
            });

        }
    }

}
