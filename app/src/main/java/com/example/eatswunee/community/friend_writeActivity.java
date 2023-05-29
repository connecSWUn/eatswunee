package com.example.eatswunee.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eatswunee.MainActivity;
import com.example.eatswunee.R;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class friend_writeActivity extends AppCompatActivity {

    public static String TAG;
    Button start_time, end_time, done, cancel;
    EditText title, content;
    Spinner spot;

    String spot_en;

    private long writer_id = 1;
    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_write);


        spot = (Spinner) findViewById(R.id.spinner_spot);

        start_time = findViewById(R.id.start_time);
        end_time = findViewById(R.id.end_time);
        done = findViewById(R.id.button_done);
        cancel = findViewById(R.id.button_cancel);

        Intent intent = getIntent();
        writer_id = intent.getExtras().getLong("writer_id");

        done.setOnClickListener(new doneBtnOnClickListener());
        start_time.setOnClickListener(new startOnClickListener());
        end_time.setOnClickListener(new endOnClickListener());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        if(spot.getSelectedItem().toString() == "구시아") spot_en = "gusia";
        else if(spot.getSelectedItem().toString() == "50주년") spot_en = "fiftieth";
        else if(spot.getSelectedItem().toString() == "누리관") spot_en = "nuri";
        else if(spot.getSelectedItem().toString() == "샬롬") spot_en = "shalom";
        else if(spot.getSelectedItem().toString() == "교직원") spot_en = "gyo";
    }


    private class doneBtnOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            retrofitClient = RetrofitClient.getInstance();
            serviceApi = RetrofitClient.getServiceApi();

            HashMap<String, Object> input = new HashMap<>();
            input.put("title", title.getText());
            input.put("spot", spot_en);
            input.put("start_time", start_time.getText());
            input.put("end_time", end_time.getText());
            input.put("content", content.getText());
            input.put("writer_id", writer_id);

            serviceApi.postData(input).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if(response.isSuccessful()) {
                        Result result = response.body();
                        Log.d("TEST", "POST Success");
                        Log.d("TEST", result.getData().getTitle());

                        Toast.makeText(friend_writeActivity.this, "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(friend_writeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(friend_writeActivity.this, "게시글 저장에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }
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