package com.jackyshan.www.pregnantmotherate.Sections.Home.Model;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseModel;

/**
 * Created by jackyshan on 1/26/16.
 */
public class VersionAdsModel extends BaseModel {
    public String message;     //广告内容
    public boolean active;     //是否激活
    public String title;         //标题
    public String androidUrl;    //android广告链接

    public static VersionAdsModel modelFromJsonMap(String json) {
        VersionAdsModel model = new VersionAdsModel();
        model.setModelByJson(json);

        return model;
    }
}
