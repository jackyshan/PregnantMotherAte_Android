package com.jackyshan.www.pregnantmotherate.Sections.Home.Model;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jackyshan on 1/25/16.
 */
public class ChoiseModel extends BaseModel {
    public int type;
    public String title;
    public String detailTitle;

    public static List<ChoiseModel> arrayfromArrayList(List list) {
        Iterator var3 = list.iterator();
        ArrayList mArr = new ArrayList();
        while(var3.hasNext()) {
            Map map = (Map)var3.next();
            ChoiseModel model = new ChoiseModel();
            model.setModelByMap(map);
            mArr.add(model);
        }

        return mArr;
    }
}
