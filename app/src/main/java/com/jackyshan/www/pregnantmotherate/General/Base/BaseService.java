package com.jackyshan.www.pregnantmotherate.General.Base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;


/**
 * Created by jackyshan on 2014/12/19.
 */
public class BaseService extends Service {

    protected void logMsg(String msg) {
        LogUtil.LogMsg(this.getClass(), msg);
    }

    protected void logErr(Exception ex) {
        LogUtil.LogErr(this.getClass(), ex);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
