package com.jackyshan.www.pregnantmotherate.Sections.Home.Next.Detail.Adapter;

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
 * Created by apple on 1/22/16.
 */
public class RecipeDetailAdapter extends BaseAdapter {

    // ViewHolder静态类
    static class ViewHolder {
        public TextView ingredientsTitle;
        public TextView ingredients;
        public TextView stepsTitle;
        public TextView steps;
        public TextView suberNameTitle;
        public TextView suberName;
        public ImageView imageView;
    }

    private LayoutInflater mInflater = null;
    private RecipeModel model;

    public RecipeDetailAdapter(Context context, RecipeModel model) {
        this.mInflater = LayoutInflater.from(context);
        this.model = model;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.detail_list_item, null);
            holder.ingredientsTitle = (TextView) convertView.findViewById(R.id.tv_ingredients_title);
            holder.ingredients = (TextView) convertView.findViewById(R.id.tv_ingredients);
            holder.stepsTitle = (TextView) convertView.findViewById(R.id.tv_steps_title);
            holder.steps = (TextView) convertView.findViewById(R.id.tv_steps);
            holder.suberNameTitle = (TextView) convertView.findViewById(R.id.tv_suberName_title);
            holder.suberName = (TextView) convertView.findViewById(R.id.tv_suberName);
            holder.imageView = (ImageView)convertView.findViewById(R.id.iv_recipe);

            // 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ingredientsTitle.setText("用料");
        holder.ingredients.setText(model.ingredients);
        holder.stepsTitle.setText("制作步骤");
        holder.steps.setText(model.steps);
        holder.suberNameTitle.setText("小贴士");
        holder.suberName.setText(model.suberName);
        ImageHelper.displayFromAssetsRecipe(model.photo, holder.imageView);

        return convertView;
    }
}
