package com.jackyshan.www.pregnantmotherate.General.Base;

import android.content.Context;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jackyshan.www.pregnantmotherate.General.Config.AppConfig;
import com.jackyshan.www.pregnantmotherate.General.Helper.CommonHelp;
import com.jackyshan.www.pregnantmotherate.General.singleton.AppContext;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by jackyshan on 15/5/10.
 */
public class BaseNetwork extends BaseObject {

    private static RequestQueue queue;
    protected String path;
    protected Map params;
    protected OnResultListener listener;
    //变量
    private String host = AppConfig.host;

    //初始化
    public BaseNetwork() {
        queue = Volley.newRequestQueue(AppContext.getInstance().getTheTopActivity());
    }

    public BaseNetwork(Context context) {
        queue = Volley.newRequestQueue(context != null ? context : AppContext.getInstance().getTheTopActivity());
    }

    //request用于继承
    public void startRequest(Map map) {

    }

    //GET请求
    protected void startGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                startNetwork(Request.Method.GET);
            }
        }.start();
    }

    //POST请求
    protected void startPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                startNetwork(Request.Method.POST);
            }
        }.start();
    }

    //网络请求
    protected void startNetwork(int method) {
        Uri.Builder builder = Uri.parse(host + path).buildUpon();

        //参数
        if (params != null && !params.isEmpty()) {
            Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();

            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        //网络请求
        logMsg("-----------网络请求开始-----------");
//        logMsg("网络请求参数:\n" + builder.toString());
        JSONObject jsonObject = new JSONObject(params);
        logMsg("网络请求参数:\n" + jsonObject);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, builder.toString(), jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //格式化打印结果
                    logMsg("请求网络成功，返回成功:\n" + CommonHelp.formatJson(response.toString()));
//                    if (response.getString("code").equals("40001")) {//请求成功
//                        //返回成功结果
//                        dealComplete(true, response.getString("data"));
//                    } else {
//                        dealComplete(false, "返回码错误");
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dealComplete(false, "JSON parse error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle your error types accordingly.For Timeout & No connection error, you can show 'retry' button.
                // For AuthFailure, you can re login with user credentials.
                // For ClientError, 400 & 401, Errors happening on client side when sending api request.
                // In this case you can check how client is forming the api and debug accordingly.
                // For ServerError 5xx, you can do retry or handle accordingly.
                if (error instanceof NetworkError) {
                    dealComplete(false, "网络错误");
                } else if (error instanceof ClientError) {
                    dealComplete(false, "客户端错误");
                } else if (error instanceof ServerError) {
                    dealComplete(false, "服务器错误");
                } else if (error instanceof AuthFailureError) {
                    dealComplete(false, "认证错误");
                } else if (error instanceof ParseError) {
                    dealComplete(false, "解析错误");
                } else if (error instanceof NoConnectionError) {
                    dealComplete(false, "网络未连接");
                } else if (error instanceof TimeoutError) {
                    dealComplete(false, "请求服务器超时");
                } else {
                    dealComplete(false, "未知错误");
                }
            }
        });

        //加入网络队列
        queue.add(jsonObjectRequest);
    }

    //处理结果
    protected void dealComplete(Boolean succ, String json) {
        if (!succ) {
            logMsg(json);
        }
    }

    //回调
    public void setOnResultListener(OnResultListener listener) {
        this.listener = listener;
    }

    public interface OnResultListener {
        void onResult(Boolean succ, List<?> list);
    }
}
