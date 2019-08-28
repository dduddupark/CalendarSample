package com.example.suyeon.calendarsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.suyeon.calendarsample.calendar.CalendarDialog;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Button mBtnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCalendar = findViewById(R.id.btn_calendar);
        mBtnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CalendarDialog dialog = new CalendarDialog();
                dialog.show(getSupportFragmentManager(), CalendarDialog.EVENT_DIALOG);
            }
        });
    }
}
