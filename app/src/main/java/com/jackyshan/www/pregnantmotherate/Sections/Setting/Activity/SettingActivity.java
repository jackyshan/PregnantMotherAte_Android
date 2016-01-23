package com.jackyshan.www.pregnantmotherate.Sections.Setting.Activity;

import android.os.Bundle;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.R;

/**
 * Created by apple on 1/23/16.
 */
public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initViews();
        setViews();
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void setViews() {
        super.setViews();
        setTitle("设置");
    }
}
