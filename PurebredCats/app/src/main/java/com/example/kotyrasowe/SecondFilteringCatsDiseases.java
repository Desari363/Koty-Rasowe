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

public class SecondFilteringCatsDiseases  extends Activity {

    private ListView listView;
    private View btnList;

    private ArrayList<SecondFilteringCatsDiseasesModel> catsD;
    private SecondFilteringCatsDiseasesListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_cat);

        listView = (ListView) findViewById(R.id.list_view);
        setListViewHeader();
        setListViewAdapter();
        setAdapterData();
        btnList.setOnClickListener(gotoSelectedListActivity()); // go to checked list

    }


    private void setListViewHeader() {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(
                R.layout.header_cats, listView, false);
        listView.addHeaderView(header, null, false);

        btnList = (Button) findViewById(R.id.button);
    }

    private void setListViewAdapter() {
        catsD = new ArrayList<SecondFilteringCatsDiseasesModel>();
        adapter = new SecondFilteringCatsDiseasesListView(this, R.layout.item_listview2, catsD);
        listView.setAdapter(adapter);
    }

    private void setAdapterData() {
        catsD.add(new SecondFilteringCatsDiseasesModel("Agresywne zachowanie na dotyk",true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Apatia",true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Biegunka", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Ból brzucha", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Brak apetytu", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Częste oddawanie moczu",false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Drżenie mięśni", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Gubienie sierści", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Kichanie",true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Konwulsje",true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Krew w moczu", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Krwotoki", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Miauczenie w kuwecie", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Nadmierne drapanie", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Nieudane próby oddania moczu", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Obrzęk brzucha",true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Omdlenia", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Osłabienie", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Osłabienie apetytu", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Paraliż", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Plamienie", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Podwyższone tętno", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Przeźroczysta wydzielina z nosa", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Przyspieszony oddech", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Senność", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Słanianie się na nogach",true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Śpiączka",false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Sztywnienie",false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Ślepota", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Ślinienie", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Utrata wagi", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Wygryzanie sierści", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Wylizywanie brzucha/ogona", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Wylizywanie sierści", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Wymioty",false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Wymioty ze śladami krwi", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Wysoka temperatura ciała", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Załatwianie się poza kuwetą", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Zaparcia", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Zmiany skórne", true));
        catsD.add(new SecondFilteringCatsDiseasesModel("Zwiększone pragnienie", false));
        catsD.add(new SecondFilteringCatsDiseasesModel("Zwiększony apetyt", true));





        adapter.notifyDataSetChanged(); // update adapter
    }


    private View.OnClickListener gotoSelectedListActivity() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(SecondFilteringCatsDiseases.this, SecondFilteringCatsDiseasesSelectedList.class);
                intent5.putParcelableArrayListExtra("Checked List", catsD);
                startActivity(intent5);
            }
        };
    }
}
