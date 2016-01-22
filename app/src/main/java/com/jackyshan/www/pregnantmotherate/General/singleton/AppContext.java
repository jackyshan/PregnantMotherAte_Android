package com.jackyshan.www.pregnantmotherate.General.singleton;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.jackyshan.www.pregnantmotherate.General.Config.AppConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackyshan on 15/5/8.
 */
public class AppContext extends Application {
    private static AppContext instance;
    private List<Activity> activityList;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //init database
        ActiveAndroid.initialize(this, true);

        //实例
        instance = this;

        //init task list
        activityList = new ArrayList<Activity>();

        //init config
        AppConfig.init();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public void finishToActivity(Class cls) {

        try {
            for (int i = activityList.size()-1; i >= 0; i--) {

                Activity activity = activityList.get(i);
                if (activity.getClass().equals(cls)) {
                    return;
                }
                activity.finish();
            }

        } catch (Exception ex) {

        }

    }

    public void addActivityIntoTask(Activity activity) {

        if (activity != null) {
            activityList.add(activity);
        }
    }

    public void removeActivityFromTask(Activity activity) {

        if (activity != null) {
            activityList.remove(activity);
        }
    }

    public void finishAllActivities() {

        for (int i = activityList.size()-1; i >= 0; i--) {

            activityList.get(i).finish();


        }
    }

    public Activity getTheTopActivity() {

        if (activityList.size() > 0) {
            return activityList.get(activityList.size()-1);
        }
        return null;
    }

    //judge if app in background
    // need permission GET_TASKS
    public boolean isApplicationBroughtToBackground() {
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(this.getPackageName())) {
                return true;
            }
        }

        return false;
    }
}
