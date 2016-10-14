package com.qf.zt.testdemo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.zt.testdemo2.R;
import com.qf.zt.testdemo2.bean.NewsPagerEntity;
import com.qf.zt.testdemo2.util.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class NewsPagerAdapter extends BaseAdapter {
    private List<NewsPagerEntity> datas;
    private LayoutInflater inflater;

    public NewsPagerAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }
    @Override
    public NewsPagerEntity getItem(int position) {
        return datas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_newspager,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        //设置内容
        NewsPagerEntity item = getItem(position);
        //设置文字内容
        viewHolder.editor.setText(item.getEditor());
        viewHolder.title.setText(item.getTitle());
        viewHolder.reviewCount.setText(item.getReviewCount()+"");
        //设置图片
        BitmapUtils.getBitmapUtils().loadImage(item.getIcon(),viewHolder.icon);

        return convertView;
    }

    public void addAll(List<NewsPagerEntity> dd) {
        datas.addAll(dd);
        notifyDataSetChanged();
    }

    class ViewHolder
    {
        private ImageView icon;
        private TextView title,editor,reviewCount;
        public ViewHolder(View convertView)
        {
            icon = ((ImageView) convertView.findViewById(R.id.item_newspager_iconId));
            title = ((TextView) convertView.findViewById(R.id.item_newspager_titleId));
            editor = ((TextView) convertView.findViewById(R.id.item_newspager_editorId));
            reviewCount = ((TextView) convertView.findViewById(R.id.item_newspager_reviewCountId));
        }
    }
}
