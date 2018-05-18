package com.hnxy.xiangyuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hnxy.middle.base.BaseActivity;
import com.hnxy.xiangyuan.view.TitleLayout;
import com.jingyu.utils.function.ActivityCollector;
import com.jingyu.utils.function.Logger;

import static com.hnxy.xiangyuan.R.id.about_us_title;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    public static final int CLICK_QUIT_INTERVAL = 1000;
    private TitleLayout hui_sheng_huo_title;
    private long lastClickQuitTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getIntentData();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        initListener();
    }

    private void getIntentData() {
    }


    @Override
    public void onBackPressed() {
        long quitTime = System.currentTimeMillis();
        if (quitTime - lastClickQuitTime <= CLICK_QUIT_INTERVAL) {
            ActivityCollector.appExit();
        } else {
            lastClickQuitTime = quitTime;
            Logger.shortToast("快速再按一次退出");
        }
    }

    public static void actionStart(Context activityContext ,int tabFragmentNum) {
        Intent intent = new Intent(activityContext, MainActivity.class);
        activityContext.startActivity(intent);

    }
    private void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        hui_sheng_huo_title = getViewById(about_us_title);
        hui_sheng_huo_title.setTitleCenter(true, "慧生活");
        hui_sheng_huo_title.setTitleLeft(R.mipmap.fanhui, "        ");
    }
    private void initListener() {

    }
    @Override
    public void onClick(View v) {

    }
}
