package com.example.eatswunee.community;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatswunee.R;

public class friend_write_spinner extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_write);

        //final TextView textView = findViewById(R.id.textView);
        Spinner spinner = findViewById(R.id.spinner_ing);
        Spinner spinner1 = findViewById(R.id.spinner_place);
        Spinner spinner2 = findViewById(R.id.spinner_time1);
        Spinner spinner3 = findViewById(R.id.spinner_time2);


        String[] kind1 = getResources().getStringArray(R.array.spinner_array_ing);
        String[] kind2 = getResources().getStringArray(R.array.spinner_array_place);
        String[] kind3 = getResources().getStringArray(R.array.spinner_array_time);
        String[] kind4 = getResources().getStringArray(R.array.spinner_array_time2);

        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner.setSelection(0);
    }
}
