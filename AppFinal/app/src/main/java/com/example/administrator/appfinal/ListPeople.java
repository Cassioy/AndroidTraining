package com.example.administrator.appfinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListPeople extends AppCompatActivity {

    private String TAG = ListPeople.class.getSimpleName();
    private ListView lista;
    ArrayList<ManagePeople> managePeople = new ArrayList<ManagePeople>();
    ListPeople context = ListPeople.this;

    ArrayList<HashMap<String, String>> birthlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_people);

        birthlist = new ArrayList<>();
        lista = (ListView) findViewById(R.id.list);
        new GetBirth().execute();
    }

    private class GetBirth extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(ListPeople.this, "JSON fazendo downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg){
            HttpHandler ht = new HttpHandler();
            String url = "URL TEXT HERE";
            String jsonStr = ht.makeGETServiceCall(url);

            Log.e(TAG, "Response from url " + jsonStr);
            if (jsonStr != null) {
                try{
                    //jsonStr = "{people: "+jsonStr+"}";
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jPeople = jsonObj.getJSONArray("people");

                    for(int i =0; i< jPeople.length();i++) {
                        JSONObject o = jPeople.getJSONObject(i);
                        String nome = o.getString("name");
                        String birth = o.getString("birth");


                        HashMap<String, String> people = new HashMap<>();

                        people.put("name", nome);
                        people.put("birth", birth);

                        birthlist.add(people);
                        //managePeople.add(new ManagePeople("",nome,birth));

                    }
                }catch (final JSONException e){
                    Log.e(TAG, "Json Parsing Error 1: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json Parsing Error 2 " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else {
                Log.e(TAG, "Não consegui retorno do JASON.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Não consegui retorno do JASON. verifique o LogCat.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
           /* ManagerPeopleAdapter androidAdapter = new ManagerPeopleAdapter(ListPeople.this, managePeople);

            ListView listView = (ListView) findViewById(R.id.people_List);

            listView.setAdapter(androidAdapter);*/


            ListAdapter adapter = new SimpleAdapter(context,
            birthlist, R.layout.list_item,
            new String[]{"name", "birth"},
            new int[]{R.id.nome, R.id.aniversario});
            lista.setAdapter(adapter);
        }
    }
}
