package com.ed.edglide.neglide;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/*图片请求对象封装类
glide.with(this)
       .load(url)
       .loading(R.drawable.loading)
       .listener(new RequestListener)
       .into(ImageView)
*/
public class BitmapRequest {

    //上下文
    private Context context;

    // 请求路径
    private String url;

    //图片标识
    private String urlMd5;

    //占位图片
    private int resID;

    //回调对象
    private RequestListener requestListener;

    //需要加载图片的控件
    private SoftReference<ImageView> imageView;


    public BitmapRequest(Context context) {
        this.context = context;
    }

    //链式调度
    public BitmapRequest load(String url) {
        this.url = url;
        this.urlMd5 = MD5Utils.toMD5(url);
        return this;
    }

    //设置占位图片
    public BitmapRequest loading(int resID) {
        this.resID = resID;
        return this;
    }

    //设置监听
    public BitmapRequest listener(RequestListener requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    //显示图片控件
    public void into(ImageView imageView){
        imageView.setTag(this.urlMd5);
        this.imageView = new SoftReference<>(imageView);

        //into方法执行的时候才把图片请求加入到requestQueue请求队列中
        //在这里创建RequestManager对象
        RequestManager.getInstance().addBitmapRequest(this);
    }


    public Context getContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public int getResID() {
        return resID;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }


    //直接获取ImageView，弱引用可以听过get方法直接获取存入的类型对象
    public ImageView getImageView() {
        return imageView.get();
    }
}
