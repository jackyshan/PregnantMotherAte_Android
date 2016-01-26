package com.jackyshan.www.pregnantmotherate.Sections.Home.Model;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseModel;

/**
 * Created by jackyshan on 1/26/16.
 */
public class VersionUpdateModel extends BaseModel {
    public String androidMessage;     //更新内容
    public boolean mustUpdate; //强制更新
    public boolean active;     //是否激活
    public String androidNewestVersion; //最新版本
    public String title;         //标题
    public String androidUrl;    //android下载链接

    public static VersionUpdateModel modelFromJsonMap(String json) {
        VersionUpdateModel model = new VersionUpdateModel();
        model.setModelByJson(json);

        return model;
    }
}
