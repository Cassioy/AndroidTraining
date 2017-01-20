package com.example.coma.gftdocesandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by coma on 19/01/2017.
 */

public class CaixaDocesAdapter extends ArrayAdapter<CaixaDoces> {

    private static final String LOG_TAG = CaixaDocesAdapter.class.getSimpleName();

    public CaixaDocesAdapter(Activity context, ArrayList<CaixaDoces> CaixaDoces) {
       super(context, 0, CaixaDoces);
   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        CaixaDoces doceAtual = getItem(position);

        TextView vNameTextView = (TextView) listItemView.findViewById(R.id.android_version);
        vNameTextView.setText(doceAtual.getAndroidVersionName());

        TextView mDoceTextView = (TextView) listItemView.findViewById(R.id.android_doces);
        mDoceTextView.setText(doceAtual.getDoce());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.doceFoto);
        iconView.setImageResource(doceAtual.getImageResourceId());

        return listItemView;
    }
}
