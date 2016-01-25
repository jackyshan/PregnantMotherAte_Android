package com.jackyshan.www.pregnantmotherate.Sections.Home.Activity;

import
        android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.General.Base.NavigationBar;
import com.jackyshan.www.pregnantmotherate.General.Helper.ProjectHelper;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter.ChoiseAdapter;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter.HomeAdapter;
import com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase.DataBaseServer;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.ChoiseModel;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Activity.RecipeDetailActivity;
import com.jackyshan.www.pregnantmotherate.Sections.Search.Activity.SearchActivity;
import com.jackyshan.www.pregnantmotherate.Sections.Setting.Activity.SettingActivity;

import java.util.List;

public class HomeActivity extends BaseActivity {
    private ListView listView;
    private HomeAdapter homeAdapter;
    private ChoiseAdapter choiseAdapter;
    private Spinner spinner;
    private List<RecipeModel> recipeModelList;
    private List<ChoiseModel> choiseModelList;

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

        setLeftImage(R.mipmap.settings);
        setLeftClick(new NavigationBar.OnLeftClickListener() {
            @Override
            public void onLeftClick() {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        setRightImage(R.mipmap.search);
        setRightClick(new NavigationBar.OnRightClickListener() {
            @Override
            public void onRightClick() {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //下拉列表
        spinner = (Spinner)findViewById(R.id.s_choise);
        setMiddleClick(new NavigationBar.OnMiddleClickListener() {
            @Override
            public void onMiddleClick() {
                updateData(null);
            }
        });
        choiseModelList = ProjectHelper.getChoiseModels();
        choiseAdapter = new ChoiseAdapter(this, choiseModelList);
        spinner.setAdapter(choiseAdapter);
        spinner.setSelection(5);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ChoiseModel model = choiseModelList.get(position);
                updateData(model);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateData(null);
            }
        });

        //获取数据库数据
        try {
            recipeModelList = DataBaseServer.selectRecipes("1");
            //adapter设置
            homeAdapter = new HomeAdapter(this, recipeModelList);
            listView.setAdapter(homeAdapter);

            //点击事件触发
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(HomeActivity.this, RecipeDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", recipeModelList.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        catch (Exception e) {
            logMsg(e.getMessage());
        }
    }

    private void  updateData(ChoiseModel model) {
        int isVisible = spinner.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
        spinner.setVisibility(isVisible);
        if (isVisible == View.VISIBLE) spinner.performClick();

        if (model == null) return;

        setTitle(model.detailTitle);
        switch (model.type) {
            case 0:
            {
                recipeModelList = DataBaseServer.selectRecipes(model.title);
                homeAdapter.list = recipeModelList;
                homeAdapter.notifyDataSetChanged();
            }
            break;
            default:
            {
                recipeModelList = DataBaseServer.selectOtherRecipes(model.title);
                homeAdapter.list = recipeModelList;
                homeAdapter.notifyDataSetChanged();
            }
                break;
        }
    }
}
