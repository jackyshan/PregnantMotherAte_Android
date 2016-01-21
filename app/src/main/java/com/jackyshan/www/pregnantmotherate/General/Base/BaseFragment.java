package com.jackyshan.www.pregnantmotherate.General.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;


/**
 * Created by jackyshan on 2014/12/12.
 */
public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    protected void logMsg(String msg) {
        LogUtil.LogMsg(this.getClass(), msg);
    }


    protected void logErr(Exception ex) {
        LogUtil.LogErr(this.getClass(), ex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       logMsg("进入界面" + this.toString());
        mActivity = (BaseActivity) getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logMsg("离开界面"+this.toString());
    }

    protected void showToast(String text) {
        showToast(text, 1000);
    }

    protected void showToast(String text, int millions) {
        Toast.makeText(mActivity, text, millions).show();
    }

    //网络请求
    protected void getData() {};

    protected void initView(LayoutInflater inflater) {};
    protected void setView() {};
}
