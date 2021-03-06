package com.jackyshan.www.pregnantmotherate.Sections.Setting.Model;

import com.activeandroid.annotation.Column;
import com.jackyshan.www.pregnantmotherate.General.Base.BaseModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jackyshan on 1/25/16.
 */
public class SettingModel extends BaseModel {

    public int type;
    public int icon;
    public String title;
    public String detailTitle;

    public SettingModel() {}

    public static List<SettingModel> arrayfromArrayList(List list) {
        Iterator var3 = list.iterator();
        ArrayList mArr = new ArrayList();
        while(var3.hasNext()) {
            Map map = (Map)var3.next();
            SettingModel model = new SettingModel();
            model.setModelByMap(map);
            mArr.add(model);
        }

        return mArr;
    }
}
