package com.example.administrator.appfinal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeUnit;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AddNewPeople extends AppCompatActivity {

    private String TAG = AddNewPeople.class.getSimpleName();
    EditText nameValue;
    Button birthDayButton;
    TextView textDate;
    int day, month, year;
    String today;
    Button submit;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_people);
        variableInicialization();

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        today = day+"/"+month+"/"+year;
        showDate(year, month, day);
        actionsInicialize();
    }



    private void variableInicialization(){
        birthDayButton = (Button) findViewById(R.id.buttonBirth);
        textDate = (TextView) findViewById(R.id.textDate);
        nameValue = (EditText) findViewById(R.id.nomeValue);
        submit = (Button) findViewById(R.id.submit);
    }

    private void actionsInicialize(){
        birthDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
                Toast.makeText(getApplicationContext(), "Type the date",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTxt = nameValue.getText().toString();
                String dtNascTxt = textDate.getText().toString();

                boolean nameIsNull = nameTxt.equals(null);
                boolean nameIsEmpty = nameTxt.equals("");

                boolean dateIsToday = dtNascTxt.equals(today);

                if(dateIsToday || nameIsEmpty || nameIsNull){
                    Toast.makeText(getApplicationContext(), "Please add name and birth date",
                            Toast.LENGTH_SHORT)
                            .show();
                }else{
                    Map<String, String> urlParams = new HashMap<String, String>();
                    urlParams.put("name", nameTxt);
                    urlParams.put("birth", dtNascTxt);
                    new PostBirth(urlParams).execute();


                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private class PostBirth extends AsyncTask<Void,Void,Void>{
        Map<String, String> urlParams;
        public PostBirth(Map<String, String> urlParams){
            this.urlParams = urlParams;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(AddNewPeople.this, "Enviando novo aniversário", Toast.LENGTH_LONG).show();
        }
        @Override
        protected Void doInBackground(Void... arg){
            HttpHandler ht = new HttpHandler();
            String url = "http://172.27.10.108:8080/app/person";
            String jsonStr = ht.makePOSTServiceCall(url, urlParams);

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
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            DatePickerDialog datePickerBirth = new DatePickerDialog(this, R.style.AlertDialogDtNasc,
                    myDateListener, year, month, day);

            return datePickerBirth;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {

                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        textDate.setText(day+"/"+month+"/"+year);
    }
}
