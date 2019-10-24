package com.example.kotyrasowe;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FilteringCatsFeaturesListView extends ArrayAdapter<FilteringCatsFeaturesModel> {

    private Activity activity;
    private ArrayList<FilteringCatsFeaturesModel> Cats;
    private final String TAG = FilteringCatsFeaturesListView.class.getSimpleName();

    public FilteringCatsFeaturesListView(Activity activity, int resource, ArrayList<FilteringCatsFeaturesModel> Cats) {
        super(activity, resource, Cats);
        this.activity = activity;
        this.Cats = Cats;
        Log.i(TAG, "init adapter");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;



        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_listview, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        FilteringCatsFeaturesModel cat = Cats.get(position);


        holder.name.setText(cat.getName());
        /*
        if (cat.isFeature()) {
            holder.image.setImageDrawable(this.activity.getResources()
                    .getDrawable(R.drawable.good));
        } else {
            holder.image.setImageDrawable(this.activity.getResources()
                    .getDrawable(R.drawable.bad));
        }
*/

        holder.check.setOnCheckedChangeListener(onCheckedChangeListener(cat));

        return convertView;
    }


    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener(final FilteringCatsFeaturesModel f) {
        return new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    f.setSelected(true);
                } else {
                    f.setSelected(false);
                }
            }
        };
    }

    private class ViewHolder {
        private ImageView image;
        private TextView name;
        private CheckBox check;

        public ViewHolder(View v) {
            //image = (ImageView) v.findViewById(R.id.feature);
            name = (TextView) v.findViewById(R.id.name);
            check = (CheckBox) v.findViewById(R.id.check);
        }
    }

}
