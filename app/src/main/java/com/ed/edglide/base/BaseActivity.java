package com.ed.edglide.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.edglide.R;
import com.ed.edglide.common.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 继承自RxAppCompatActivity，定义了常见方法如：创建文件、初始化数据库、初始化配置信息等；
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    private BaseActivity thisActivity = null;

    private ImageView leftIv;
    private TextView middleTitleTv;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, initLayout(), null);
        setContentView(needHeader() ? getMergerView(view) : view);
        //绑定初始化ButterKnife
        unbinder = ButterKnife.bind(this);

        //沉浸式状态栏
        StatusBarUtils.translucent(this);
        thisActivity = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        thisActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    /**
     * @return 初始化布局
     */
    protected abstract int initLayout();

    protected boolean needHeader() {
        return true;
    }


    private View getMergerView(@NonNull View v) {

        View rootView;
        LinearLayout baseContainerRl;

        rootView = View.inflate(this, R.layout.title_bar_frag, null);

        leftIv = rootView.findViewById(R.id.iv_left_btn);
        middleTitleTv = rootView.findViewById(R.id.tv_middle_title);

        baseContainerRl = rootView.findViewById(R.id.ll_base_container);
        baseContainerRl.addView(v);

        leftBtnClickListener();
        return rootView;
    }


    /**
     * 左边按钮点击事件
     */

    private void leftBtnClickListener() {
        leftIv.setOnClickListener(v -> finish());
    }

    protected void setTitle(String title) {
        if (middleTitleTv != null) {
            middleTitleTv.setText(title);
        }
    }

    public BaseActivity getThisActivity() {
        return thisActivity;
    }


    public String getThisActivityName() {
        return thisActivity.getClass().getName();
    }

    public void gotoActivity(Class<?> clazz) {
        thisActivity.startActivity(new Intent(thisActivity, clazz));
    }

    public void gotoActivity(Class<?> clazz, boolean isFinish) {
        thisActivity.startActivity(new Intent(thisActivity, clazz));
        if (isFinish) {
            thisActivity.finish();
        }
    }

    public void gotoActivity(
            @NonNull Class<?> clazz, @NonNull Bundle bundle) {
        Intent intent = new Intent(thisActivity, clazz);
        intent.putExtras(bundle);
        thisActivity.startActivity(intent);
    }

    public void gotoActivity(
            @NonNull Class<?> clazz, @NonNull Bundle bundle, @NonNull boolean isFinish) {
        Intent intent = new Intent(thisActivity, clazz);
        intent.putExtras(bundle);
        thisActivity.startActivity(intent);
        if (isFinish) {
            thisActivity.finish();
        }
    }


    /**
     * 判断某activity是否处于栈顶
     *
     * @return true在栈顶 false不在栈顶
     */
    private boolean isActivityTop(Class cls, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(cls.getName());
    }


}
