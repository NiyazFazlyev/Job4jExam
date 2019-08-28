package ru.job4j.exam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class DateActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final String DATE = "DateActivity";

    //    private TextView text;
    private StringBuilder dateAndTime = new StringBuilder();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);
//        text = findViewById(R.id.dateAndTime);

    }

    public void showDatePickerDialog(View v) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");
        Log.d(DATE, "Click Button");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
//        text.setText(String.format("%s.%s.%s", day, month + 1, year));
        if (view.getTag() == null) {
            view.setTag("TAGGED");
            dateAndTime.append(String.format("%s.%s.%s", day, month + 1, year));
//            TextView text = findViewById(R.id.dateAndTime);
//            text.setText(dateAndTime.toString());
//            Log.d(DATE, "Select Date");
            DialogFragment timeFragment = new TimePickerFragment();
            timeFragment.show(getSupportFragmentManager(), "timePicker");

        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (view.getTag() == null) {
            view.setTag("TAGGED");
            dateAndTime
                    .append("\n")
                    .append(String.format("%s:%s", hourOfDay, minute));
            TextView text = findViewById(R.id.dateAndTime);
            text.setText(dateAndTime.toString());
        }
    }
}
