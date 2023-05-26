package com.example.eatswunee.community;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.eatswunee.R;

public class friend_writeActivity extends AppCompatActivity {

    public static String TAG;
    Spinner spinner_status;
    Button start_time, end_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_write);

        spinner_status = (Spinner) findViewById(R.id.spinner_status);

        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array_status, R.layout.spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_status.setAdapter(adapter);

        start_time.setOnClickListener(new startOnClickListener());
        end_time.setOnClickListener(new endOnClickListener());
    }

    private class startOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TimePickerPopupDialogTwoButton octDialog = new TimePickerPopupDialogTwoButton(friend_writeActivity.this, new TimePickerPopupDialogClickListener() {
                @Override
                public void onPositiveClick(int setHourValue, int setMinuteValue) {
                    Log.d(TAG, "onPositiveClick : " + setHourValue + "시 " + setMinuteValue + "분");
                    if(setMinuteValue < 10) start_time.setText(setHourValue + ":0" + setMinuteValue);
                    else start_time.setText(setHourValue + ":" + setMinuteValue);
                }

                @Override
                public void onNegativeClick() {
                    Log.d(TAG, "No click");
                }
            });

            octDialog.setCanceledOnTouchOutside(false);
            octDialog.setCancelable(true);
            octDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            octDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            octDialog.show();
        }
    }

    private class endOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TimePickerPopupDialogTwoButton octDialog = new TimePickerPopupDialogTwoButton(friend_writeActivity.this, new TimePickerPopupDialogClickListener() {
                @Override
                public void onPositiveClick(int setHourValue, int setMinuteValue) {
                    Log.d(TAG, "onPositiveClick : " + setHourValue + "시 " + setMinuteValue + "분");
                    if(setMinuteValue < 10) end_time.setText(setHourValue + ":0" + setMinuteValue);
                    else end_time.setText(setHourValue + ":" + setMinuteValue);
                }

                @Override
                public void onNegativeClick() {
                    Log.d(TAG, "No click");
                }
            });

            octDialog.setCanceledOnTouchOutside(false);
            octDialog.setCancelable(true);
            octDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            octDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            octDialog.show();
        }
    }
}