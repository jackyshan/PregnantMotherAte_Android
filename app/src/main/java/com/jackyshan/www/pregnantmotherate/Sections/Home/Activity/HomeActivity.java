package com.jackyshan.www.pregnantmotherate.Sections.Home.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter.HomeAdapter;
import com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase.DataBaseServer;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Activity.RecipeDetailActivity;

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
            final List<RecipeModel> list = DataBaseServer.selectRecipes("1");
            //adapter设置
            adapter = new HomeAdapter(this, list);
            listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(adapter);

            //点击事件触发
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(HomeActivity.this, RecipeDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", list.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        catch (Exception e) {
            logMsg(e.getMessage());
        }
    }
}
