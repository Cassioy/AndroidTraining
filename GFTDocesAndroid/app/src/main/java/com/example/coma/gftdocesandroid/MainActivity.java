package com.example.coma.gftdocesandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<CaixaDoces> androidDoces = new ArrayList<CaixaDoces>();

        androidDoces.add(new CaixaDoces("1.6", "Donut", R.drawable.donut));
        androidDoces.add(new CaixaDoces("2.1", "Eclair", R.drawable.eclair));
        androidDoces.add(new CaixaDoces("X.X", "XXXXXX", R.drawable.froyo));
        androidDoces.add(new CaixaDoces("X.X", "XXXXXX", R.drawable.honeycomb));
        androidDoces.add(new CaixaDoces("X.X", "XXXXXX", R.drawable.icecream));
        androidDoces.add(new CaixaDoces("X.X", "XXXXXX", R.drawable.jellybean));
        androidDoces.add(new CaixaDoces("X.X", "XXXXXX", R.drawable.kitkat));
        androidDoces.add(new CaixaDoces("X.X", "XXXXXX", R.drawable.lollipop));
        androidDoces.add(new CaixaDoces("X.X", "XXXXXX", R.drawable.marshmallow));


        CaixaDocesAdapter androidAdapter = new CaixaDocesAdapter(this, androidDoces);

        ListView listView = (ListView) findViewById(R.id.list_view_doces);
        listView.setAdapter(androidAdapter);
    }
}
