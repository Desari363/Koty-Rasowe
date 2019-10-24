package com.example.kotyrasowe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CatBreedsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CatBreedsModel> catBreedsModelArrayList;


    public CatBreedsAdapter(Context context, ArrayList<CatBreedsModel> catBreedsModelArrayList) {

        this.context = context;
        this.catBreedsModelArrayList = catBreedsModelArrayList;

    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return catBreedsModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return catBreedsModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.iv = (ImageView) convertView.findViewById(R.id.imgView);

            convertView.setTag(holder);
        }else {

            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText(catBreedsModelArrayList.get(position).getName());
       // holder.iv.setImageResource(catBreedsModelArrayList.get(position).getCat_bitmap());
        holder.iv.setImageBitmap(catBreedsModelArrayList.get(position).getCat_bitmap());
        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname;
        private ImageView iv;

    }

}