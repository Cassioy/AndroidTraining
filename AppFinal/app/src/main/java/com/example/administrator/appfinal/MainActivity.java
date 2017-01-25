package com.example.administrator.appfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout newBirthAction;
    RelativeLayout listAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startItems();

        setActions();
    }

    private void startItems(){
        newBirthAction =  (RelativeLayout) findViewById(R.id.newBirthAction);
        listAction =  (RelativeLayout) findViewById(R.id.listAction);
    }

    private void setActions(){
        newBirthAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddNewPeople.class);
                startActivity(intent);
            }
        });

        listAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListPeople.class);
                startActivity(intent);
            }
        });
    }
}
