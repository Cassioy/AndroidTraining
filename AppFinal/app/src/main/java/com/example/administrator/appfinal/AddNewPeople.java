package com.example.administrator.appfinal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewPeople extends AppCompatActivity {

    EditText nameValue;
    Button birthDayButton;
    TextView textDate;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_people);

        variableInicialization();
        year = Calendar.YEAR;
        month = Calendar.MONTH;
        day = Calendar.DAY_OF_MONTH;
        textDate = (TextView) findViewById(R.id.textDate);
        showDate(year, month+1, day);
        actionsInicialize();
    }

    private void variableInicialization(){
        birthDayButton = (Button) findViewById(R.id.buttonBirth);
        nameValue = (EditText) findViewById(R.id.nomeValue);
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
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
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
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        textDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
