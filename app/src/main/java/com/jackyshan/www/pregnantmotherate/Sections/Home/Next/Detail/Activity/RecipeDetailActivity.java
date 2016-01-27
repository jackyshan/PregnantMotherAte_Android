package com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.jackyshan.www.pregnantmotherate.General.Base.BaseActivity;
import com.jackyshan.www.pregnantmotherate.General.Base.NavigationBar;
import com.jackyshan.www.pregnantmotherate.General.Helper.ImageHelper;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase.DataBaseServer;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Adapter.RecipeDetailAdapter;

import org.json.JSONObject;

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

        //添加百度广告
        addBaiduAd();
    }



    private void addBaiduAd() {
        //人群属性
        AdSettings.setKey(new String[]{"baidu", "中国"});
        //创建广告view
        String adPlaceId = "2402253";//重要：请填上你的代码位ID,否则无法请求到广告
        AdView adView = new AdView(this, adPlaceId);
        //设置监听器
        adView.setListener(new AdViewListener() {
            @Override
            public void onAdReady(AdView adView) {

            }

            @Override
            public void onAdShow(JSONObject jsonObject) {

            }

            @Override
            public void onAdClick(JSONObject jsonObject) {

            }

            @Override
            public void onAdFailed(String s) {

            }

            @Override
            public void onAdSwitch() {

            }
        });

        //将adView添加到父控件中（注：该父控件不一定为您的根控件，只要该控件能通过addView添加广告视图即可）
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.ll_bottom_ad);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        rllp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linearLayout.addView(adView, rllp);
    }
}
