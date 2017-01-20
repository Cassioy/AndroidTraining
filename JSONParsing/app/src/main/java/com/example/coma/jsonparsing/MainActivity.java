package com.example.coma.jsonparsing;

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

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lista;

    ArrayList<HashMap<String, String>> contactslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      contactslist = new ArrayList<>();
        lista = (ListView) findViewById(R.id.list);
       new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "JSON fazendo downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg){
            HttpHandler ht = new HttpHandler();
            String url = "http://api.androidhive.info/contacts/";
            String jsonStr = ht.makeServiceCall(url);

            Log.e(TAG, "Response from url " + jsonStr);
            if (jsonStr != null) {
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    for(int i =0; i< contacts.length();i++) {
                        JSONObject o = contacts.getJSONObject(i);
                        String nome = o.getString("name");
                        String email = o.getString("email");

                        JSONObject phone = o.getJSONObject("phone");
                        String mobile = phone.getString("mobile");

                        HashMap<String, String> contato = new HashMap<>();

                        contato.put("name", nome);
                        contato.put("email", email);
                        contato.put("mobile", mobile);

                        contactslist.add(contato);

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
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                    contactslist, R.layout.list_item,
                    new String[]{"nome", "email", "mobile"},
                    new int[]{R.id.nome, R.id.email, R.id.mobile});
            lista.setAdapter(adapter);
        }
    }
}
