package com.jackyshan.www.pregnantmotherate.Sections.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jackyshan.www.pregnantmotherate.R;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.ChoiseModel;

import java.util.List;

/**
 * Created by jackyshan on 1/25/16.
 */
public class ChoiseAdapter extends BaseAdapter {
    // ViewHolder静态类
    static class ViewHolder {
        public TextView choiseTitle;
    }

    private LayoutInflater mInflater = null;
    public List<ChoiseModel> list;

    public ChoiseAdapter(Context context, List<ChoiseModel> list) {
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

            convertView = mInflater.inflate(R.layout.spinner_list_item, null);
            holder.choiseTitle = (TextView)convertView.findViewById(R.id.tv_detail);
            // 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChoiseModel model = list.get(position);

        holder.choiseTitle.setText(model.detailTitle);

        return convertView;
    }
}
