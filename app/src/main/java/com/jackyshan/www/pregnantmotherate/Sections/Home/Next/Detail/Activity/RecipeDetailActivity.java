package com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.General.Base.NavigationBar;
import com.jackyshan.www.pregnantmotherate.General.Helper.ImageHelper;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase.DataBaseServer;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Adapter.RecipeDetailAdapter;

/**
 * Created by jackyshan on 1/22/16.
 */
public class RecipeDetailActivity extends BaseActivity {
    private RecipeModel model;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        setViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        model = (RecipeModel)getIntent().getSerializableExtra("key");

        listView = (ListView)findViewById(R.id.listView);
    }

    @Override
    protected void setViews() {
        super.setViews();

        setTitle(model.name);
        setRightImage(model.star ? R.mipmap.star_data_selected : R.mipmap.star_data);
        setRightClick(new NavigationBar.OnRightClickListener() {
            @Override
            public void onRightClick() {
                model.star = !model.star;
                DataBaseServer.updateStarRecipe(model);
                setRightImage(model.star ? R.mipmap.star_data_selected : R.mipmap.star_data);
            }
        });

        RecipeDetailAdapter adapter = new RecipeDetailAdapter(this, model);
        listView.setAdapter(adapter);
    }
}
