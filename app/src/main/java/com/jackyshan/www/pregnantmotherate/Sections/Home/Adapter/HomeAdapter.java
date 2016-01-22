package com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackyshan.www.pregnantmotherate.General.Helper.ImageHelper;
import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;

import java.util.List;

/**
 * Created by jackyshan on 1/21/16.
 */
public class HomeAdapter extends BaseAdapter {

    // ViewHolder静态类
    static class ViewHolder {
        public ImageView iconImgV;
        public TextView recipeTitle;
        public TextView recipeDetail;
    }

    private LayoutInflater mInflater = null;
    private List<RecipeModel> list;

    public HomeAdapter(Context context, List<RecipeModel> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.home_list_item, null);
            holder.iconImgV = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.recipeTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.recipeDetail = (TextView) convertView.findViewById(R.id.tv_detail);

            // 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        RecipeModel model = list.get(position);

        ImageHelper.displayFromAssetsRecipe(model.photo, holder.iconImgV);
        holder.recipeTitle.setText(model.name);
        holder.recipeDetail.setText(model.suberName);

        return convertView;
    }
}