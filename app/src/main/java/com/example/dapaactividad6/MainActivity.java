package com.example.dapaactividad6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button datePicker;
    TextView currentDate, birthDate, currentAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.button);
        currentDate =  findViewById(R.id.currentDate);
        birthDate = findViewById(R.id.birthDate);
        currentAge = findViewById(R.id.currentAge);
    }

    @SuppressLint("SetTextI18n")
    public void OnClick(View v) {
        Calendar calendar = Calendar.getInstance();

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        currentDate.setText(getResources().getString(R.string.current_date) + "\n" + currentDay + "/" + (currentMonth + 1) + "/" + currentYear);

        DatePickerDialog chooseDate = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            birthDate.setText(getResources().getString(R.string.birth_date) + "\n" + dayOfMonth + "/" + (month + 1) + "/" + year);
            currentAge.setText(getResources().getString(R.string.current_age) + "\n" + calculateAge(currentYear, currentMonth, currentDay, year, month, dayOfMonth));
        }, currentYear, currentMonth, currentDay);

        chooseDate.show();
    }

    public String calculateAge(int currentYear, int currentMonth, int currentDay, int birthYear, int birthMonth, int birthDay) {
        int calcYear = currentYear - birthYear;

        int calcMonth = currentMonth - birthMonth;

        if (calcMonth < 0) {
            calcYear--;
            calcMonth = calcMonth + 12;
        } else if (calcMonth == 0){
            if (!(currentDay >= birthDay)) {
                calcYear--;
                calcMonth = 11;
            }
        }

        return "You are " + calcYear + " years and " + calcMonth + " months old.";
    }
}