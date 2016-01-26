package com.jackyshan.www.pregnantmotherate.General.Config;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;

import com.jackyshan.www.pregnantmotherate.General.singleton.AppContext;
import com.jackyshan.www.pregnantmotherate.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by jackyshan on 15/5/8.
 */
public class AppConfig {

    /**
     * this is userInfo flag
     */
    public final static String flag_isLog = "isLog";

    //logPath
    public final static String AppName = AppContext.getInstance().getString(R.string.app_name);
    public final static String appMainPath = Environment.getExternalStorageDirectory().getPath() +
            File.separator + AppName + File.separator;
    public static final String logPath = appMainPath + "log" + File.separator;

    //host
    public static final String host = "http://139.162.4.196:5002/";

    //config
    public static final String APP_ID = "wx37a6f261598ba82e";

    //变量定义
    private static Context context;
    private static SharedPreferences userInfo;

    //初始化
    public static void init() {
        context  = AppContext.getInstance();
        userInfo = context.getSharedPreferences("Info_Config", 0);
        setUserDefault(flag_isLog, true);


        //Android-Universal-Image-Loader 第三方图片库初始化
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

        //桌面图标
        addShortcut();
    }

    //获取用户默认信息
    public static <T> T getUserDefault(String flag, Class<T> cls) {

        try {
            Object result = null;

            if (cls.equals(boolean.class)) {
                result = userInfo.getBoolean(flag, false);
            } else if (cls.equals(String.class)) {
                result = userInfo.getString(flag, null);
            } else if (cls.equals(int.class)) {
                result = userInfo.getInt(flag, -1);
            } else if (cls.equals(long.class)) {
                result = userInfo.getLong(flag, 0);
            }
            return (T) result;

        } catch (Exception ex) {
            LogUtil.LogErr(AppConfig.class, ex);
        }
        return null;
    }

    //获取用户默认信息
    public static void setUserDefault(String flag, Object obj) {

        if (obj instanceof Boolean) {
            userInfo.edit().putBoolean(flag, (Boolean) obj).commit();

        } else if (obj instanceof String) {
            userInfo.edit().putString(flag, (String) obj).commit();
        } else if (obj instanceof Integer) {
            userInfo.edit().putInt(flag, (Integer) obj).commit();
        } else if (obj instanceof Long) {
            userInfo.edit().putLong(flag, (Long) obj).commit();
        }
    }

    //是否开启日志功能
    public static boolean isLogOn() {

        if (getUserDefault(flag_isLog, boolean.class)) {
            return true;
        }
        return false;
    }

    //屏幕宽度
    public static int getScreenWidth() {

        int screen_width    = userInfo.getInt("screen_width", -1);

        return screen_width;
    }

    //屏幕宽度
    public static int getScreenHeight() {

        int screen_height   = userInfo.getInt("screen_height", -1);

        return screen_height;
    }


    //添加删除桌面快捷方式
    public static void addShortcut() {
        if (hasShortcut(context)) {
            //LogUtil.LogMsg(context.getClass(),"-------------已经存在图标----------------");
            return;
        }
        LogUtil.LogMsg(context.getClass(),"-------------添加图标完成----------------");
        // 创建快捷方式的Intent
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutIntent.putExtra("duplicate", false);
        // 快捷方式的名称
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                context.getString(R.string.app_name));
        // 快捷图片,一个Parcelable对象
        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                context, R.mipmap.ic_launcher);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");

        // 点击快捷图片，运行的程序主入口
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        context.sendBroadcast(shortcutIntent);
    }

    //判断桌面快捷方式是否已经存在
    public static boolean hasShortcut(Context cx) {
        boolean result = false;
        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = cx.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(cx.getPackageName(), PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }

        final String uriStr;
        if (android.os.Build.VERSION.SDK_INT < 8) {
            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
        } else {
            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = cx.getContentResolver().query(CONTENT_URI, null, "title=?", new String[]{title}, null);
        if (c != null && c.getCount() > 0) {
            result = true;
        }
        return result;
    }
}