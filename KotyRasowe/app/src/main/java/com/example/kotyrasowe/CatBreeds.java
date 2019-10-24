package com.example.kotyrasowe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class CatBreeds extends AppCompatActivity {

    public EditText imie;
    public Button query_button;
    private ListView lv;
    private CatBreedsAdapter catBreedsAdapter;
    private ArrayList<CatBreedsModel> catBreedsModelArrayList;

    public List<Bitmap> catImageList;
    private List<String> catNameList;

    // WPISANIE NAZWY W WYSZUKIWARKE I WYŚWIETLENIE OPISU WPISANEJ RASY KOTA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

        databaseAccess.open();
        catNameList = databaseAccess.getStringList("Select Nazwa from Rasy");
        catImageList= databaseAccess.getBitmapList("Select Obraz from Rasy ",getResources());

        databaseAccess.close();

        imie = findViewById(R.id.imie);
        query_button = findViewById(R.id.query_button);


        query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nazwa = imie.getText().toString();

                databaseAccess.open();
                String opis = databaseAccess.getString("select Opis from Rasy where Nazwa ='" + nazwa + "'");
                databaseAccess.close();

                Intent intent = new Intent(CatBreeds.this, SpecificationCat.class);
                intent.putExtra("animal", opis);
                startActivity(intent);
            }
        });


    // LISTVIEW

        lv = (ListView) findViewById(R.id.listView);

        catBreedsModelArrayList = populateList();
        Log.d("hjhjh", catBreedsModelArrayList.size() + "");
        catBreedsAdapter = new CatBreedsAdapter(this, catBreedsModelArrayList);
        lv.setAdapter(catBreedsAdapter);


        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(CatBreeds.this, SpecificationCat.class);

                String name = catNameList.get(position);
                databaseAccess.open();
                String description = databaseAccess.getString("select Opis from Rasy where Nazwa ='" + name + "'");
                databaseAccess.close();

                intent.putExtra("animal", description);
                startActivity(intent);
            }
        });

    }

    private ArrayList<CatBreedsModel> populateList() {

        ArrayList<CatBreedsModel> list = new ArrayList<>();

        for (int i = 0; i < catNameList.size(); i++) {
            CatBreedsModel catBreedsModel = new CatBreedsModel();
            catBreedsModel.setName(catNameList.get(i));
            catBreedsModel.setCat_bitmap(catImageList.get(i));
            list.add(catBreedsModel);
        }

        return list;

    }

}
