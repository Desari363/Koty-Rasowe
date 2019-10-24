package com.example.kotyrasowe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class FilteringCatsFeatures extends Activity {

    private ListView listView;
    private View btnList;

    private ArrayList<FilteringCatsFeaturesModel> cats;
    private FilteringCatsFeaturesListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering_cat);

        listView = (ListView) findViewById(R.id.list_view);
        setListViewHeader();
        setListViewAdapter();
        setAdapterData();
        btnList.setOnClickListener(gotoSelectedListActivity());

    }


    private void setListViewHeader() {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(
                R.layout.header_cats, listView, false);
        listView.addHeaderView(header, null, false);

        btnList = (Button) findViewById(R.id.button);
    }

    private void setListViewAdapter() {
        cats = new ArrayList<FilteringCatsFeaturesModel>();
        adapter = new FilteringCatsFeaturesListView(this, R.layout.item_listview, cats);
        listView.setAdapter(adapter);
    }

    private void setAdapterData() {
        cats.add(new FilteringCatsFeaturesModel("Aktywny", true));
        cats.add(new FilteringCatsFeaturesModel("Cichy", true));
        cats.add(new FilteringCatsFeaturesModel("Czuły", false));
        cats.add(new FilteringCatsFeaturesModel("Przystosowujący", true));
        cats.add(new FilteringCatsFeaturesModel("Energiczny", false));
        cats.add(new FilteringCatsFeaturesModel("Figlarny", false));
        cats.add(new FilteringCatsFeaturesModel("Inteligentny",false));
        cats.add(new FilteringCatsFeaturesModel("Kochający", false));
        cats.add(new FilteringCatsFeaturesModel("Lubiący wodę",true));
        cats.add(new FilteringCatsFeaturesModel("Niezależny", true));
        cats.add(new FilteringCatsFeaturesModel("Pewny", true));
        cats.add(new FilteringCatsFeaturesModel("Przyjazny",false));
        cats.add(new FilteringCatsFeaturesModel("Spokojny",true));
        cats.add(new FilteringCatsFeaturesModel("Towarzyski", false));
        cats.add(new FilteringCatsFeaturesModel("Wierny", false));
        cats.add(new FilteringCatsFeaturesModel("Wokalny", false));
        cats.add(new FilteringCatsFeaturesModel("Wrażliwy", true));
        cats.add(new FilteringCatsFeaturesModel("Zrównoważony", false));
        cats.add(new FilteringCatsFeaturesModel("Żywy", true));
        adapter.notifyDataSetChanged(); // update adapter
    }


    private View.OnClickListener gotoSelectedListActivity() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(FilteringCatsFeatures.this, FilteringCatsFeaturesSelectedList.class);
                intent4.putParcelableArrayListExtra("Checked List", cats);
                startActivity(intent4);
            }
        };
    }
}