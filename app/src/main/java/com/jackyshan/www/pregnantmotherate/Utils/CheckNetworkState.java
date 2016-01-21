package com.jackyshan.www.pregnantmotherate.Utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;

public class CheckNetworkState {

    public final static String WIFI = "wifi";


    protected void logMsg(String msg) {
        LogUtil.LogMsg(this.getClass(), msg);
    }

    protected void logErr(Exception ex) {
        LogUtil.LogErr(this.getClass(), ex);
    }

	/**
	 * 检测wifi状态
	 *
	 * @return
	 */
	public static boolean getWIFIState(Context context) {
		boolean iscon = false;
		// TODO Auto-generated method stub
		try {
			ConnectivityManager cManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			State wifi = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
					.getState();

			if (wifi == State.CONNECTED) {
				iscon = true;
			}

		} catch (Exception e) {
			new CheckNetworkState().logErr(e);
		}
		return iscon;
	}

	/**
	 * 检测gprs状态
	 *
	 * @return
	 */
	public static boolean getApnState(Context context) {
		boolean iscon = false;
		// TODO Auto-generated method stub
		try {
			ConnectivityManager cManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo nif=cManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			State apn =nif==null? State.UNKNOWN:nif.getState();

			if (apn == State.CONNECTED) {
				iscon = true;
			}

		} catch (Exception e) {
            new CheckNetworkState().logErr(e);
		}
		return iscon;
	}

	public static boolean hasNetWork(Context context) {
		try {

			boolean apn = CheckNetworkState.getApnState(context);
			boolean wifi = CheckNetworkState.getWIFIState(context);

			if (apn || wifi) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
            new CheckNetworkState().logErr(e);
		}
		return false;
	}

    public static boolean setNetWork(final Context context){
      try {
//          if (!hasNetWork(context)){
              AlertDialog.Builder dialog = new AlertDialog.Builder(context);
              dialog.setTitle("网络连接已断开");
              dialog.setMessage("是否进行设置");
              dialog.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                      Intent intent=null;
                      //判断手机系统的版本  即API大于10 就是3.0或以上版本
                      if(android.os.Build.VERSION.SDK_INT>10){
                          intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                      }else{
                          intent = new Intent();
                          ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                          intent.setComponent(component);
                          intent.setAction("android.intent.action.VIEW");
                      }
                      context.startActivity(intent);

                  }

              });

              dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                  public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                  }

              });
              dialog.show();
//          }

          } catch (Exception e) {
                new CheckNetworkState().logErr(e);
          }
        return true;
    }

    public static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }


}
