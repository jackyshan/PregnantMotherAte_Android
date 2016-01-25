package com.jackyshan.www.pregnantmotherate.General.Base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;
import com.jackyshan.www.pregnantmotherate.General.singleton.AppContext;
import com.jackyshan.www.pregnantmotherate.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jackyshan on 2014/12/8.
 */
public class BaseActivity extends FragmentActivity {

    public final static String toFlag = "toActivity";
    protected Class toCls;

    //navigationBar
    protected NavigationBar navigationBar;

    private final static Map<Class, List<Class>> rulesMap = new HashMap<Class, List<Class>>() {
        {

        }
    };

    protected void logMsg(String msg) {
        LogUtil.LogMsg(this.getClass(), msg);
    }

    protected void logErr(Exception ex) {
        LogUtil.LogErr(this.getClass(), ex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logMsg("进入界面" + this.toString());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ((AppContext)getApplication()).addActivityIntoTask(this);

        toCls = (Class) getIntent().getSerializableExtra(toFlag);
        checkToCls();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        toCls = (Class) intent.getSerializableExtra(toFlag);
        checkToCls();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        logMsg("离开界面" + this.toString());

        ((AppContext)getApplication()).removeActivityFromTask(this);
    }

    protected void initViews() {
        navigationBar = (NavigationBar) findViewById(R.id.navibar);
    }

    protected void setViews() {
        navigationBar.setOnLeftClickListener(new NavigationBar.OnLeftClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }
        });
    }

    protected void setTitle(String text) {
        navigationBar.setTitle(text);
    }

    protected void setLeftGone() {
        navigationBar.setLeftGone();
        navigationBar.setOnLeftClickListener(null);
    }

    protected void setLeftImage (int resID) {
        navigationBar.setLeftImageRes(resID);
    }

    protected void setLeftClick (NavigationBar.OnLeftClickListener listener){
        navigationBar.setOnLeftClickListener(listener);
    }

    protected void setMiddleClick(NavigationBar.OnMiddleClickListener listener) {
        navigationBar.setOnMiddleClickListener(listener);
    }

    protected void setRightImage (int resID) {
        navigationBar.setRightImageRes(resID);
    }

    protected void setRightClick (NavigationBar.OnRightClickListener listener){
        navigationBar.setOnRightClickListener(listener);
    }

    protected void showToast(String text) {
        showToast(text, 1000);
    }

    protected void showToast(String text, int millions) {
        Toast.makeText(this, text, millions).show();
    }

    //网络请求
    protected void getData() {};

    private void
    checkToCls() {

        try {

            if (toCls != null) {
                if (toCls != this.getClass()) {
                    if (rulesMap.containsKey(toCls)) {

                        List<Class> ruleList = rulesMap.get(toCls);

                        if (ruleList.contains(getClass())) {

                            int index = ruleList.indexOf(getClass());
                            Class nextClass = ruleList.get(index+1);

                            Intent intent = new Intent();
                            intent.setClass(this, nextClass);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.putExtra(toFlag, toCls);

                            startActivity(intent);

                        }
                    }
                }
            }

        } catch (Exception ex) {
            logErr(ex);
        }

    }

}
