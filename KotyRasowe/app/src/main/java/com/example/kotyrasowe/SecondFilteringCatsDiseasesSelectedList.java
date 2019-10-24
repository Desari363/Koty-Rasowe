package com.example.kotyrasowe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondFilteringCatsDiseasesSelectedList extends AppCompatActivity {


    private ListView lv2;
    private CatBreedsAdapter catDiseaseAdapter;
    private ArrayList<CatBreedsModel> catDiseaseModelArrayList = new ArrayList<>();

    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        lv2 = (ListView) findViewById(R.id.listView);

        ArrayList<SecondFilteringCatsDiseasesModel> checkboxes = new ArrayList<SecondFilteringCatsDiseasesModel>();


        checkboxes = getIntent().getParcelableArrayListExtra("Checked List");

        ArrayList<String> selectedDisease = filterSelectedNames(checkboxes);

        populateImageAndDescriptionLists(selectedDisease);
        Log.d("hjhjh", catDiseaseModelArrayList.size() + "");
        catDiseaseAdapter = new CatBreedsAdapter(this, catDiseaseModelArrayList);
        lv2.setAdapter(catDiseaseAdapter);


        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(SecondFilteringCatsDiseasesSelectedList.this, SpecificationCat.class);

                String name = catDiseaseModelArrayList.get(position).getName();

                databaseAccess.open();
                String description = databaseAccess.getString("select Opis from OpisyChorób where Nazwa ='" + name + "'");
                databaseAccess.close();


                intent.putExtra("animal", description);
                startActivity(intent);
            }
        });

    }

    private void populateImageAndDescriptionLists(ArrayList<String> filteredList) {
        databaseAccess.open();
        String queryStrings = "Select Nazwa, Opis, count(Nazwa) as c from OpisyChorób INNER join ObjawyChorób on OpisyChorób._id = ObjawyChoróbPołączenia._id INNER join ObjawyChoróbPołączenia on ObjawyChorób._id_objawu = ObjawyChoróbPołączenia._id_objawu WHERE " ;
        String queryBitmaps = "Select Obraz, count(Nazwa) as c from OpisyChorób INNER join ObjawyChorób on OpisyChorób._id = ObjawyChoróbPołączenia._id INNER join ObjawyChoróbPołączenia on ObjawyChorób._id_objawu = ObjawyChoróbPołączenia._id_objawu WHERE  ";
        for (int i = 0; i < filteredList.size(); i = i + 1) {
            if (i != filteredList.size() - 1) {
                queryStrings = queryStrings +"Objaw = " + "\"" + filteredList.get(i) + "\"" + " OR ";
                queryBitmaps = queryBitmaps +"Objaw = " + "\"" + filteredList.get(i) + "\"" + " OR "; }
            else {
                queryStrings = queryStrings + "Objaw = " + "\"" + filteredList.get(i) + "\"" + " GROUP BY Nazwa HAVING c = " + filteredList.size();
                queryBitmaps = queryBitmaps + "Objaw = " + "\"" + filteredList.get(i) + "\"" + " GROUP BY Nazwa HAVING c = " + filteredList.size();
            }
        }

        //UZYCIE TEGO ZAPYTNIA W FUNKCJI KTORA ZWRACA LISTE LIST Z WARTOSCIAMI KOLUMN
        List<List<String>> cechyy = databaseAccess.getColumnsValues(queryStrings, 2);
        List<Bitmap> bitmaps = databaseAccess.getBitmapList(queryBitmaps, getResources());
        //STWORZENIE CATBREEDSMODEL I PRZYPISANIE DO NIEGO ZDJECIA, NAZWY I OPISU JAK W KODZIE KTORY MASZ POWYZEJ, UZYWAJAC LISTY LIST OTRZYMANEJ WYZEJ
        databaseAccess.close();
        for (int i = 0; i < cechyy.get(0).size(); i = i + 1) {
            CatBreedsModel catBreedsModel = new CatBreedsModel();
            catBreedsModel.setName(cechyy.get(0).get(i));
            catBreedsModel.setCat_bitmap(bitmaps.get(i));
            catDiseaseModelArrayList.add(catBreedsModel);
        }
    }


    private ArrayList<String> filterSelectedNames(ArrayList<SecondFilteringCatsDiseasesModel> checkboxes) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < checkboxes.size(); i = i + 1) {
            if (checkboxes.get(i).isSelected())
                result.add(checkboxes.get(i).getName());
        }

        return result;
    }
}
