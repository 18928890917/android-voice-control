package com.hnxy.xiangyuan.activity;

import android.os.Bundle;
import android.view.View;

import com.hnxy.xiangyuan.R;
import com.hnxy.xiangyuan.view.TitleLayout;
import com.hnxy.middle.base.BaseActivity;

/**
 * Created by qishaog on 2017/5/28.
 */

public class QSG_AboutUsActivity extends BaseActivity implements View.OnClickListener {
    private TitleLayout about_us_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
        initListener();

    }

    private void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        about_us_title = getViewById(R.id.about_us_title);
        about_us_title.setTitleCenter(true, "关于");
        about_us_title.setTitleLeft(R.mipmap.fanhui, "        ");
    }

    private void initListener() {

    }
    @Override
    public void onClick(View v) {

    }
}
