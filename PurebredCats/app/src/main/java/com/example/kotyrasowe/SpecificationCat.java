package com.example.kotyrasowe;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

public class SpecificationCat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spcification_cat);

        String savedExtra = getIntent().getStringExtra("animal");
        TextView myText = (TextView) findViewById(R.id.result);
        myText.setText(savedExtra);
    }

}
