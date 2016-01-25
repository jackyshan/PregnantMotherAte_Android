package com.jackyshan.www.pregnantmotherate.Sections.Setting.Next.Star.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter.HomeAdapter;
import com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase.DataBaseServer;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Activity.RecipeDetailActivity;

import java.util.List;

/**
 * Created by jackyshan on 1/25/16.
 */
public class StarRecipesActivity extends BaseActivity {
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
    }

    @Override
    protected void setViews() {
        super.setViews();

        setTitle("我的收藏");

        Spinner spinner = (Spinner)findViewById(R.id.s_choise);
        spinner.setVisibility(View.GONE);

        //获取数据库数据
        try {
            final List<RecipeModel> list = DataBaseServer.selectStarRecipes();
            //adapter设置
            HomeAdapter adapter = new HomeAdapter(this, list);
            ListView listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(adapter);

            //点击事件触发
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(StarRecipesActivity.this, RecipeDetailActivity.class);
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
