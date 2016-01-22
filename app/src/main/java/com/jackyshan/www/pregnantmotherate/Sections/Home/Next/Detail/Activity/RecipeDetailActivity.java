package com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Activity;

import android.os.Bundle;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;

/**
 * Created by jackyshan on 1/22/16.
 */
public class RecipeDetailActivity extends BaseActivity {
    private RecipeModel model;

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
    }

    @Override
    protected void setViews() {
        super.setViews();

        setTitle(model.name);
    }
}
