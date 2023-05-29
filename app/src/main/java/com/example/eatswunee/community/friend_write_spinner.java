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

        Spinner spinner = findViewById(R.id.spinner_spot);

        String[] kind2 = getResources().getStringArray(R.array.spinner_array_place);

        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}


