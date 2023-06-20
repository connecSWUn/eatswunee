package com.example.eatswunee.bistro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eatswunee.R;

public class review_writeActivity extends AppCompatActivity {

    private Button cancel, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        cancel = findViewById(R.id.review_cancel_btn);
        done = findViewById(R.id.review_done_btn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}