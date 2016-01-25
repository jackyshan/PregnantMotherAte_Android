package com.jackyshan.www.pregnantmotherate.Sections.Setting.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.General.Helper.ProjectHelper;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Setting.Adapter.SettingAdapter;
import com.jackyshan.www.pregnantmotherate.Sections.Setting.Model.SettingModel;
import com.jackyshan.www.pregnantmotherate.Sections.Setting.Next.Star.Activity.StarRecipesActivity;

import java.util.List;

/**
 * Created by apple on 1/23/16.
 */
public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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
        setTitle("设置");

        final List<SettingModel> list = ProjectHelper.getSettingModels();
        logMsg(list.toString());
        SettingAdapter adapter = new SettingAdapter(this, list);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SettingModel model = list.get(position);
                switch (model.type) {
                    case 0:
                    {
                        Intent intent = new Intent(SettingActivity.this, StarRecipesActivity.class);
                        startActivity(intent);
                    }
                        break;
                    default:break;
                }
            }
        });
    }
}
