package com.jackyshan.www.pregnantmotherate.Sections.Search.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter.HomeAdapter;
import com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase.DataBaseServer;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Activity.RecipeDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 1/23/16.
 */
public class SearchActivity extends BaseActivity {
    private List<RecipeModel> list;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        setViews();
    }

    @Override
    protected void initViews() {
        super.initViews();
        list = new ArrayList<>();
    }

    @Override
    protected void setViews() {
        super.setViews();

        setTitle("搜索");

        adapter = new HomeAdapter(this, list);
        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //点击事件触发
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, RecipeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        final EditText editText = (EditText)findViewById(R.id.et_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        list = DataBaseServer.selectSearchRecipes(editable.toString());
                        adapter.list = list;

                        //主线程更新ui
                        listView.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
