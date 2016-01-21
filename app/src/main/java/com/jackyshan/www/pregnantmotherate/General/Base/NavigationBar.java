package com.jackyshan.www.pregnantmotherate.General.Base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackyshan.www.pregnantmotherate.R;

/**
 * Created by jackyshan on 2015/1/6.
 */
public class NavigationBar extends FrameLayout {

    private Context context;

    FrameLayout fl_left;
    FrameLayout fl_middle;
    FrameLayout fl_right;
    ImageView iv_left;
    ImageView iv_right;
    TextView tv_left;
    TextView tv_middle;
    TextView tv_right;

    public interface OnLeftClickListener {
        void onLeftClick();
    }
    public interface OnMiddleClickListener {
        void onMiddleClick();
    }
    public interface OnRightClickListener {
        void onRightClick();
    }

    private OnLeftClickListener     leftClickListener;
    private OnMiddleClickListener   middleClickListener;
    private OnRightClickListener    rightClickListener;

    public NavigationBar(Context context) {
        super(context);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        setView();
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.context = context;
        setView();
    }

    private void setView() {

        LayoutInflater.from(context).inflate(R.layout.navigationbar, this);

        fl_left = (FrameLayout) findViewById(R.id.navigationbar_fl_left);
        fl_middle = (FrameLayout) findViewById(R.id.navigationbar_fl_middle);
        fl_right = (FrameLayout) findViewById(R.id.navigationbar_fl_right);

        iv_left = (ImageView) findViewById(R.id.navigationbar_iv_left);
        iv_right = (ImageView) findViewById(R.id.navigationbar_iv_right);

        tv_left = (TextView) findViewById(R.id.navigationbar_tv_left);
        tv_middle = (TextView) findViewById(R.id.navigationbar_tv_middle);
        tv_right = (TextView) findViewById(R.id.navigationbar_tv_right);
    }

    public void setLeftGone() {

        iv_left.setVisibility(GONE);
        tv_left.setVisibility(GONE);

    }

    public void setLeftVisible() {

        iv_left.setVisibility(VISIBLE);
        tv_left.setVisibility(VISIBLE);
    }

    public void setRightGone() {

        iv_right.setVisibility(GONE);
        tv_right.setVisibility(GONE);

    }

    public void setRightVisible() {

        iv_right.setVisibility(VISIBLE);
        tv_right.setVisibility(VISIBLE);
    }

    public void setLeftString(String s) {
        tv_left.setVisibility(VISIBLE);
        tv_left.setText(s);
    }

    public void setLeftImageRes(int resID) {
        iv_left.setVisibility(VISIBLE);
        iv_left.setImageResource(resID);
    }

    public void setLeftImageDrawable(Drawable drawable) {
        iv_left.setVisibility(VISIBLE);
        iv_left.setImageDrawable(drawable);
    }

    public void setLeftImageBitmap(Bitmap bitmap) {
        iv_left.setVisibility(VISIBLE);
        iv_left.setImageBitmap(bitmap);
    }

    public void setRightString(String s) {
        tv_right.setVisibility(VISIBLE);
        tv_right.setText(s);
    }

    public void setRightImageRes(int resID) {
        iv_right.setVisibility(VISIBLE);
        iv_right.setImageResource(resID);
    }

    public void setRightImageDrawable(Drawable drawable) {
        iv_right.setVisibility(VISIBLE);
        iv_right.setImageDrawable(drawable);
    }

    public void setRightImageBitmap(Bitmap bitmap) {
        iv_right.setVisibility(VISIBLE);
        iv_right.setImageBitmap(bitmap);
    }

    public void setTitle(String s) {
        tv_middle.setText(s);
    }

    public void setOnLeftClickListener(OnLeftClickListener listener) {
        this.leftClickListener = listener;
        fl_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftClickListener != null) {
                    leftClickListener.onLeftClick();
                }
            }
        });
    }

    public void setOnMiddleClickListener(OnMiddleClickListener listener) {
        this.middleClickListener = listener;
        fl_middle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (middleClickListener != null) {
                    middleClickListener.onMiddleClick();
                }
            }
        });
    }

    public void setOnRightClickListener(OnRightClickListener listener) {
        this.rightClickListener = listener;
        fl_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightClickListener != null) {
                    rightClickListener.onRightClick();
                }
            }
        });
    }

}
