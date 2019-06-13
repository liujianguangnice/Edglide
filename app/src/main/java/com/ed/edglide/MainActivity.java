package com.ed.edglide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ed.edglide.base.BaseActivity;

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
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_moreImage:
                Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
