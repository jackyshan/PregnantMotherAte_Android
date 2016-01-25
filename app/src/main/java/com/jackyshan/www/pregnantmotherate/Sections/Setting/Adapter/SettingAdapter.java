package com.jackyshan.www.pregnantmotherate.Sections.Setting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Setting.Model.SettingModel;

import java.util.List;

/**
 * Created by jackyshan on 1/25/16.
 */
public class SettingAdapter extends BaseAdapter {

    // ViewHolder静态类
    static class ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView detailTitle;
    }

    private LayoutInflater mInflater = null;
    public List<SettingModel> list;

    public SettingAdapter(Context context, List<SettingModel> list) {
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

            convertView = mInflater.inflate(R.layout.setting_list_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.detailTitle = (TextView) convertView.findViewById(R.id.tv_detail);
            View view = (View) convertView.findViewById(R.id.v_bottom);
            if (position == 1) view.setVisibility(View.GONE);

            // 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        SettingModel model = list.get(position);

        holder.title.setText(model.title);
        holder.icon.setImageResource(model.icon);
        holder.detailTitle.setText(model.detailTitle);

        return convertView;
    }
}
