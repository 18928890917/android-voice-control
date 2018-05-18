package com.hnxy.xiangyuan.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hnxy.xiangyuan.R;
import com.hnxy.middle.Image;
import com.jingyu.utils.util.UtilView;

public class TitleLayout extends FrameLayout {

    private RelativeLayout titlebarLayout;
    private LinearLayout leftLayout;
    private ImageView leftImageview;
    private TextView leftTextview;
    private TextView centerTextview;
    private LinearLayout rightLayout;
    private ImageView rightImageview;
    private LinearLayout rightLayout2;
    private TextView rightTextview2;
    private LinearLayout rightImageviewLayout2;
    private ImageView righImageview2;

    public TitleLayout(Context context) {
        super(context);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.view_titlebar, this, true);
        titlebarLayout = (RelativeLayout) findViewById(R.id.titlebarLayout);
        leftLayout = (LinearLayout) findViewById(R.id.leftLayout);
        leftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        leftImageview = (ImageView) findViewById(R.id.leftImageview);
        leftTextview = (TextView) findViewById(R.id.leftTextview);
        centerTextview = (TextView) findViewById(R.id.centerTextview);
        rightLayout = (LinearLayout) findViewById(R.id.rightLayout);
        rightImageview = (ImageView) findViewById(R.id.rightImageview);
        rightLayout2 = (LinearLayout) findViewById(R.id.rightLayout2);
        rightTextview2 = (TextView) findViewById(R.id.rightTextview2);
        rightImageviewLayout2 = (LinearLayout) findViewById(R.id.rightImageViewLayout2);
        righImageview2 = (ImageView) findViewById(R.id.rightImageview2);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置title的中心的标题
     */
    public void setTitleCenter(boolean isCenterShow, String title) {
        UtilView.setGone(isCenterShow, centerTextview);
        centerTextview.setText(title);
    }

    /**
     * 设置左边的文本, 设置true , 如果是"" 则显示的就是一个返回的图标,
     * 如果是"返回" ,则为一个返回的图标+"返回"的字样
     */
    public void setTitleLeft(boolean isLeftShow, String leftText) {
        UtilView.setGone(isLeftShow, leftLayout);
        leftTextview.setText(leftText);
    }

    public void setTitleLeft(int leftDrawableId, String leftText) {
        UtilView.setGone(true, leftLayout);
        leftTextview.setText(leftText);
        if (leftDrawableId > 0) {
            leftImageview.setImageResource(leftDrawableId);
            UtilView.setGone(true, leftImageview);
        } else {
            UtilView.setGone(false, leftImageview);
        }
    }


    /**
     * title中间的textview也可以设置drawable , 这里默认的是textview的右边的drawable
     */
    public void setTitleCenterRightDrawable(int textviewDrawableId) {
        if (textviewDrawableId > 0) {
            Drawable drawable = getResources().getDrawable(textviewDrawableId);//R.drawable. d_arrow_down
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            centerTextview.setCompoundDrawables(null, null, drawable, null);
        } else {
            centerTextview.setCompoundDrawables(null, null, null, null);
        }
    }

    /**
     * 如果这个显示, 那么TitleRight2就不必要显示了, 这个最右边的布局只有一个图片
     */
    public void setTitleRight(boolean isRightShow, int rightDrawableId) {
        UtilView.setGone(isRightShow, rightLayout);
        rightImageview.setBackgroundResource(rightDrawableId);
    }

    /**
     * 这个布局为 textview + iamgeview , isRight2Show如果为false,则该right2_layout不显示,
     * right2_drawable_id如果小于0 ,则imageview不显示 ,text如果为null,则textview不显示
     */
    public void setTitleRight2(boolean isRight2Show, int right2DrawableId, String text) {

        UtilView.setGone(isRight2Show, rightLayout2);

        if (right2DrawableId > 0) {
            righImageview2.setBackgroundResource(right2DrawableId);
            UtilView.setGone(true, righImageview2);
        } else {
            UtilView.setGone(false, righImageview2);
            UtilView.setGone(false, rightImageviewLayout2);
        }

        if (text != null) {
            UtilView.setGone(true, rightTextview2);
            rightTextview2.setText(text);
        } else {
            UtilView.setGone(false, rightTextview2);
        }
    }
    /**
     * 这个布局为ImageLoader加载网络图片
     * textview + iamgeview , isRight2Show如果为false,则该right2_layout不显示,
     * right2_drawable_id如果小于0 ,则imageview不显示 ,text如果为null,则textview不显示
     */
    public void setTitleRight2(boolean isRight2Show,String url, int defaultDrawableId, String text) {

        UtilView.setGone(isRight2Show, rightLayout2);

        if (defaultDrawableId > 0) {
            righImageview2.setImageResource(defaultDrawableId);
            Image.displayImage(url,righImageview2);
            UtilView.setGone(true, righImageview2);
        } else {
            UtilView.setGone(false, righImageview2);
            UtilView.setGone(false, rightImageviewLayout2);
        }

        if (text != null) {
            UtilView.setGone(true, rightTextview2);
            rightTextview2.setText(text);
        } else {
            UtilView.setGone(false, rightTextview2);
        }
    }

    public RelativeLayout getTitlebarLayout() {
        return titlebarLayout;
    }

    public LinearLayout getLeftLayout() {
        return leftLayout;
    }

    public ImageView getLeftImageview() {
        return leftImageview;
    }

    public TextView getLeftTextview() {
        return leftTextview;
    }

    public TextView getCenterTextview() {
        return centerTextview;
    }

    public LinearLayout getRightLayout() {
        return rightLayout;
    }

    public ImageView getRightImageview() {
        return rightImageview;
    }

    public LinearLayout getRightLayout2() {
        return rightLayout2;
    }

    public TextView getRightTextview2() {
        return rightTextview2;
    }

    public LinearLayout getRightImageviewLayout2() {
        return rightImageviewLayout2;
    }

    public ImageView getRighImageview2() {
        return righImageview2;
    }
}
