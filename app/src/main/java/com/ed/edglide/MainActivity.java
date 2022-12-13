package com.ed.edglide;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ed.edglide.base.BaseActivity;
import com.ed.edglide.neglide.Glide;
import com.ed.edglide.neglide.RequestListener;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_oneImage)
    Button btOneImage;
    @BindView(R.id.bt_moreImage)
    Button btMoreImage;
    @BindView(R.id.ll_images)
    LinearLayout llImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.bt_oneImage, R.id.bt_moreImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_oneImage:
                single();
                break;
            case R.id.bt_moreImage:
                more();
                break;
        }
    }

    private void more() {
        llImages.removeAllViews();

        for (int i = 1; i <= 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            llImages.addView(imageView);

            String url = null;
            switch (i){
                case 1:
                    url = "https://inews.gtimg.com/newsapp_bt/0/15128231645/1000";
                    break;
                case 2:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562161200909&di=0628697ac3386ae4422e4fc58ea6943e&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171123%2Fb988fb2283a54564ae91ec837907a384.jpeg";
                    break;
                case 3:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562756511&di=04279229d0d53a050da3002de5ab4e7f&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.pcgames.com.cn%2Fimages%2Fpiclib%2F201109%2F28%2Fbatch%2F1%2F111494%2F1317214085325fs99s8q70l_medium.jpg";
                    break;
                case 4:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562161815489&di=86833967e5d30028f4f586642dc6533f&imgtype=0&src=http%3A%2F%2Fwww.33lc.com%2Farticle%2FUploadPic%2F2012-8%2F2012818585520420.jpg";
                    break;
                case 5:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562161888552&di=4c0a37aea31ef78e241818bf21729596&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-03-15%2F5aaa40beaa0cf.jpg";
                    break;
                case 6:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562161940754&di=f96a9d23b0f8c81f27b165b84fba3c90&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-05-24%2F5b0648dc7dc3a.jpg";
                    break;
                case 7:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562161940754&di=323a328761c1d2025adaa9679493234c&imgtype=0&src=http%3A%2F%2Fimg.netbian.com%2Ffile%2F2017%2F1104%2F2a5e8f2654d0570c4a533280599c7eeb.jpg";
                    break;
                case 8:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562161940753&di=5f9c71be4b620620b5eb1d80642bae34&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fa%2F548e4799c86e6.jpg";
                    break;
                case 9:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562756721&di=fdad1677977edace0c22f06284750d8a&imgtype=jpg&er=1&src=http%3A%2F%2Fi1.17173cdn.com%2F2fhnvk%2FYWxqaGBf%2Fcms3%2FTTEvcNbjEmxyuAB.jpg";
                    break;
                case 10:
                    url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562162118331&di=37b99c45e89caec3c06dfa9828c5f446&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20150626%2FImg415673984.jpg";
                    break;
            }

            Glide.with(this)
                    .load(url+"")
                    .loading(R.mipmap.loading)
                    .listener(new RequestListener() {
                        @Override
                        public boolean onSuccess(Bitmap bitmap) {
                            return false;
                        }

                        @Override
                        public boolean onFailure() {
                            return false;
                        }
                    })
                    .into(imageView);
        }
    }

    private void single() {
        llImages.removeAllViews();

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        llImages.addView(imageView);

        Glide.with(this)
                .load("https://hbimg.b0.upaiyun.com/fbd709caf6d4212323d849f28e6aeccba068dc9930aab7-0dI3RV_fw658")
                .loading(R.mipmap.loading)
                .listener(new RequestListener() {
                    @Override
                    public boolean onSuccess(Bitmap bitmap) {
                        Toast.makeText(getThisActivity(), "coming1", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onFailure() {
                        return false;
                    }
                })
                .into(imageView);

    }
}
