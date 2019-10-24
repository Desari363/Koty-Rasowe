package com.example.kotyrasowe;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FilteringCatsFeaturesSelectedList extends AppCompatActivity {


    private ListView lv;
    private CatBreedsAdapter catBreedsAdapter;
    private ArrayList<CatBreedsModel> catBreedsModelArrayList = new ArrayList<>();
    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        lv = (ListView) findViewById(R.id.listView);

        ArrayList<FilteringCatsFeaturesModel> checkboxes = new ArrayList<FilteringCatsFeaturesModel>();

        checkboxes = getIntent().getParcelableArrayListExtra("Checked List");

        ArrayList<String> selectedFeatures = filterSelectedNames(checkboxes);

        populateImageAndDescriptionLists(selectedFeatures);
        Log.d("hjhjh", catBreedsModelArrayList.size() + "");
        catBreedsAdapter = new CatBreedsAdapter(this, catBreedsModelArrayList);
        lv.setAdapter(catBreedsAdapter);


        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(FilteringCatsFeaturesSelectedList.this, SpecificationCat.class);


                String name = catBreedsModelArrayList.get(position).getName();
                databaseAccess.open();
                String description = databaseAccess.getString("select Opis from Rasy where Nazwa ='" + name + "'");
                databaseAccess.close();

                intent.putExtra("animal", description);
                startActivity(intent);
            }
        });

    }

    private void populateImageAndDescriptionLists(ArrayList<String> filteredList) {
        //ZAPYTANIE DO BAZY DANYCH KTORE ZWROCI KOTY Z PODANYMI CEHAMI Z FILTEREDLIST, ICH OPISY I ZDJECIA

        databaseAccess.open();
        String queryStrings = "Select Nazwa, Opis, count(Nazwa) as c from Rasy INNER join CechyKotów on Rasy._id = CechyKotówPołączenia._id INNER join CechyKotówPołączenia on CechyKotów.id_cechy = CechyKotówPołączenia.id_cechy WHERE " ;
        String queryBitmaps = "Select Obraz, count(Nazwa) as c from Rasy INNER join CechyKotów on Rasy._id = CechyKotówPołączenia._id INNER join CechyKotówPołączenia on CechyKotów.id_cechy = CechyKotówPołączenia.id_cechy WHERE ";
        for (int i = 0; i < filteredList.size(); i = i + 1) {
            if (i != filteredList.size() - 1) {
                queryStrings = queryStrings +"Cechy = " + "\"" + filteredList.get(i) + "\"" + " OR ";
                queryBitmaps = queryBitmaps +"Cechy = " + "\"" + filteredList.get(i) + "\"" + " OR "; }
            else {
                queryStrings = queryStrings + "Cechy = " + "\"" + filteredList.get(i) + "\"" + " GROUP BY Nazwa HAVING c = " + filteredList.size();
                queryBitmaps = queryBitmaps + "Cechy = " + "\"" + filteredList.get(i) + "\"" + " GROUP BY Nazwa HAVING c = " + filteredList.size();
            }
        }

        //String queryBitmaps = queryStrings.replace(",Opis", "").replace("Nazwa", "Obraz");

        //UZYCIE TEGO ZAPYTNIA W FUNKCJI KTORA ZWRACA LISTE LIST Z WARTOSCIAMI KOLUMN
        List<List<String>> cechyy = databaseAccess.getColumnsValues(queryStrings, 2);
        List<Bitmap> bitmaps = databaseAccess.getBitmapList(queryBitmaps, getResources());
        //STWORZENIE CATBREEDSMODEL I PRZYPISANIE DO NIEGO ZDJECIA, NAZWY I OPISU JAK W KODZIE KTORY MASZ POWYZEJ, UZYWAJAC LISTY LIST OTRZYMANEJ WYZEJ
        databaseAccess.close();
        for (int i = 0; i < cechyy.get(0).size(); i = i + 1) {
                CatBreedsModel catBreedsModel = new CatBreedsModel();
                catBreedsModel.setName(cechyy.get(0).get(i));
                catBreedsModel.setCat_bitmap(bitmaps.get(i));
                catBreedsModelArrayList.add(catBreedsModel);
        }
    }



    private ArrayList<String> filterSelectedNames(ArrayList<FilteringCatsFeaturesModel> checkboxes) {
        ArrayList<String> result = new ArrayList<String>();

        for(int i = 0; i < checkboxes.size(); i = i + 1){
            if(checkboxes.get(i).isSelected())
                result.add(checkboxes.get(i).getName());
        }

        return result;
    }
}

