package com.example.kotyrasowe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MenuActivity extends AppCompatActivity {

    private Button LaunchActivity1;
    private Button LaunchActivity2;
    private Button LaunchActivity3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        LaunchActivity1 = (Button) findViewById(R.id.button);

        LaunchActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity1();
            }
        });
        LaunchActivity2 = (Button) findViewById(R.id.button2);

        LaunchActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity2();
            }
        });
        LaunchActivity3 = (Button) findViewById(R.id.button3);

        LaunchActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity3();
            }
        });
    }

    private void launchActivity1() {

        Intent intent = new Intent(this, CatBreeds.class);
        startActivity(intent);
    }
    private void launchActivity2() {

        Intent intent2 = new Intent(this, FilteringCatsFeatures.class);
        startActivity(intent2);
    }
    private void launchActivity3() {

        Intent intent3 = new Intent(this, SecondFilteringCatsDiseases.class);
        startActivity(intent3);
    }
}
