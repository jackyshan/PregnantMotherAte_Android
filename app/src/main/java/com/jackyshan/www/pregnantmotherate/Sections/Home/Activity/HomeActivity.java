package com.jackyshan.www.pregnantmotherate.Sections.Home.Activity;

import android.os.Bundle;
import android.widget.ListView;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter.HomeAdapter;
import com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase.DataBaseServer;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;

import java.util.List;

public class HomeActivity extends BaseActivity {
    private ListView listView;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        listView = (ListView)findViewById(R.id.listView);
    }

    @Override
    protected void setViews() {
        super.setViews();
        setTitle("Home");
        navigationBar.setLeftGone();

        //获取数据库数据
        try {
            List<RecipeModel> list = DataBaseServer.selectRecipes("1");
            //adapter设置
            adapter = new HomeAdapter(this, list);
            listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
        catch (Exception e) {
            logMsg(e.getMessage());
        }
    }
}
