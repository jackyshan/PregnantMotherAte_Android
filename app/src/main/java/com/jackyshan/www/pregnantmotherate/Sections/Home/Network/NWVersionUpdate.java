package com.jackyshan.www.pregnantmotherate.Sections.Home.Network;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseNetwork;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.VersionUpdateModel;

import java.util.Map;

/**
 * Created by jackyshan on 1/22/16.
 */
public class NWVersionUpdate extends BaseNetwork {
    @Override
    public void startRequest(Map map) {
        super.startRequest(map);

        path = "pregnantmother/app/version/update";
        params = map;
        startPost();
    }

    @Override
    protected void dealComplete(Boolean succ, String json) {
        super.dealComplete(succ, json);

        if (succ) {
            modelListener.OnResult(true, VersionUpdateModel.modelFromJsonMap(json));
        }
    }
}
