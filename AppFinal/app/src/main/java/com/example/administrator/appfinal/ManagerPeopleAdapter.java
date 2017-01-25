package com.example.administrator.appfinal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafs on 23/01/2017.
 */

public class ManagerPeopleAdapter extends ArrayAdapter<ManagePeople>{

    private static final String LOG_TAG = ManagerPeopleAdapter.class.getSimpleName();

    public ManagerPeopleAdapter(final Context context, final List<ManagePeople> managePeople) {
        super(context, 0, managePeople);
    }

    public View getView(final int position, final View convertView, final ViewGroup parent){
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false
            );
        }

        ManagePeople personActual = getItem(position);

        TextView nomePerson = (TextView) listView.findViewById(R.id.nome);
        nomePerson.setText(personActual.getvName());

        TextView niverPerson = (TextView) listView.findViewById(R.id.aniversario);
        niverPerson.setText(personActual.getvBirth());

        return listView;
    }
}
