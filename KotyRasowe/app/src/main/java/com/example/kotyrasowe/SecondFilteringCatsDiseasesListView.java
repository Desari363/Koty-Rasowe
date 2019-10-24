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

public class SecondFilteringCatsDiseasesListView extends ArrayAdapter<SecondFilteringCatsDiseasesModel> {

    private Activity activity1;
    private ArrayList<SecondFilteringCatsDiseasesModel> CatsD;
    private final String TAG = SecondFilteringCatsDiseasesListView.class.getSimpleName();

    public SecondFilteringCatsDiseasesListView(Activity activity1, int resource1, ArrayList<SecondFilteringCatsDiseasesModel> CatsD) {
        super(activity1, resource1, CatsD);
        this.activity1 = activity1;
        this.CatsD = CatsD;
        Log.i(TAG, "init adapter");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SecondFilteringCatsDiseasesListView.ViewHolder holder = null;


        LayoutInflater inflater = (LayoutInflater) activity1
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_listview2, parent, false);

            holder = new SecondFilteringCatsDiseasesListView.ViewHolder(convertView);

            convertView.setTag(holder);
        } else {

            holder = (SecondFilteringCatsDiseasesListView.ViewHolder) convertView.getTag();
        }

        SecondFilteringCatsDiseasesModel cat = CatsD.get(position);


        holder.name.setText(cat.getName());
        if (cat.isDiseases()) {
            holder.image.setImageDrawable(this.activity1.getResources()
                    .getDrawable(R.drawable.diseases));
        } else {
            holder.image.setImageDrawable(this.activity1.getResources()
                    .getDrawable(R.drawable.diseases));
        }


        holder.check.setOnCheckedChangeListener(onCheckedChangeListener(cat));

        return convertView;
    }


    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener(final SecondFilteringCatsDiseasesModel f) {
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
            image = (ImageView) v.findViewById(R.id.diseases);
            name = (TextView) v.findViewById(R.id.name);
            check = (CheckBox) v.findViewById(R.id.check);
        }
    }

}
