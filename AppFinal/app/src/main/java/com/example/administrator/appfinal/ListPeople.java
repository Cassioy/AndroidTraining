package com.example.administrator.appfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListPeople extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_people);

        ArrayList<ManagePeople> people = new ArrayList<ManagePeople>();

        people.add(new ManagePeople("", "Bruna", "03/11/1996"));
        people.add(new ManagePeople("", "Thiago", "19/03/1996"));
        people.add(new ManagePeople("", "Mariana", "20/08/2000"));
        people.add(new ManagePeople("", "Thayná", "03/11/1996"));
        people.add(new ManagePeople("", "Aline", "03/11/1996"));
        people.add(new ManagePeople("", "Michel", "03/11/1996"));
        people.add(new ManagePeople("", "Claudinei", "03/11/1996"));
        people.add(new ManagePeople("", "Magda", "03/11/1996"));
        people.add(new ManagePeople("", "Rafael", "03/11/1996"));
        people.add(new ManagePeople("", "Alice", "03/11/1996"));
        people.add(new ManagePeople("", "Camila", "03/11/1996"));
        people.add(new ManagePeople("", "Katia", "03/11/1996"));

        ManagerPeopleAdapter peopleAdapter = new ManagerPeopleAdapter(this, people);

        ListView listView = (ListView) findViewById(R.id.people_List);
        listView.setAdapter(peopleAdapter);
    }
}
