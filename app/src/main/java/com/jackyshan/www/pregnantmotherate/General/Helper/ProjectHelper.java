package com.jackyshan.www.pregnantmotherate.General.Helper;

import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.ChoiseModel;
import com.jackyshan.www.pregnantmotherate.Sections.Setting.Model.SettingModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jackyshan on 1/25/16.
 */
public class ProjectHelper {

    /**
     * 设置页面
     *
     * @return list
     */
    public static List<SettingModel> getSettingModels() {

        List list = new ArrayList() {
            {
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("icon", R.mipmap.star);
                        put("title", "我的收藏");
                        put("detailTitle", "");
                    }
                });
                add(new HashMap() {

                    {
                        put("type", 1);
                        put("icon", R.mipmap.version);
                        put("title", "版本");
                        put("detailTitle", CommonHelp.getVersion());
                    }
                });
            }
        };
        return SettingModel.arrayfromArrayList(list);
    }

    public static List<ChoiseModel> getChoiseModels() {
        List list = new ArrayList() {
            {
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("title", "1");
                        put("detailTitle", "1月");
                    }
                });
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("title", "2");
                        put("detailTitle", "2月");
                    }
                });
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("title", "3");
                        put("detailTitle", "3月");
                    }
                });
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("title", "4");
                        put("detailTitle", "4月");
                    }
                });
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("title", "5");
                        put("detailTitle", "5月");
                    }
                });
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("title", "6");
                        put("detailTitle", "6月");
                    }
                });
                add(new HashMap() {

                    {
                        put("type", 0);
                        put("title", "7");
                        put("detailTitle", "7月");
                    }
                });
            }
        };
        return ChoiseModel.arrayfromArrayList(list);
    }
}
