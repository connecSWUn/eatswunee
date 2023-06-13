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

import com.example.eatswunee.R;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class friend_writeActivity extends AppCompatActivity {

    public static String TAG;
    Button start_time_btn, end_time_btn, done, cancel;
    EditText article_title, article_content;
    Spinner spot;

    String title, recruitStatus, start_time, end_time, recruit_spot, content;

    private long writer_id = 1;
    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_write);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        spot = (Spinner) findViewById(R.id.spinner_spot);

        start_time_btn = findViewById(R.id.start_time);
        end_time_btn = findViewById(R.id.end_time);
        done = findViewById(R.id.button_done);
        cancel = findViewById(R.id.button_cancel);
        article_title = findViewById(R.id.editText_title);
        article_content = findViewById(R.id.write_content);

        done.setOnClickListener(new doneBtnOnClickListener());
        start_time_btn.setOnClickListener(new startOnClickListener());
        end_time_btn.setOnClickListener(new endOnClickListener());

        Intent intent = getIntent();
        if(intent.getExtras().getBoolean("edit") == true) {
            long postId = intent.getExtras().getLong("postId");
            serviceApi.getData("recruit", postId).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Result result = response.body();
                    Data data = result.getData();
                    Log.d("retrofit", "Data fetch success");

                    article_title.setText(data.getTitle());
                    article_content.setText(data.getContent());
                    spot.setSelection(((ArrayAdapter) spot.getAdapter()).getPosition(data.getSpot()));
                    start_time_btn.setText(data.getStart_time());
                    end_time_btn.setText(data.getEnd_time());

                    done.setText("수정");
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public class doneBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            title = article_title.getText().toString();
            recruitStatus = "CONNECTING";
            start_time = start_time_btn.getText().toString();
            end_time = end_time_btn.getText().toString();
            content = article_content.getText().toString();

            if(spot.getSelectedItem().toString() == "구시아") recruit_spot = "gusia";
            else if(spot.getSelectedItem().toString() == "50주년") recruit_spot = "fiftieth";
            else if(spot.getSelectedItem().toString() == "누리관") recruit_spot = "nuri";
            else if(spot.getSelectedItem().toString() == "샬롬") recruit_spot = "shalom";
            else if(spot.getSelectedItem().toString() == "교직원") recruit_spot = "gyo";

            article article = new article(title, recruitStatus, start_time, end_time, recruit_spot, content);

            serviceApi.postArticle(article).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()) {
                        Result result = response.body();
                        Log.d("article", "POST Success");

                        long post_id = result.getData().getPost_id();

                        Toast.makeText(friend_writeActivity.this, "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(friend_writeActivity.this, friend_viewActivity.class);
                        intent.putExtra("recruitId", post_id);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(friend_writeActivity.this, "게시글 저장에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                }
            });

            finish();
            overridePendingTransition(0,0);
        }
    }

    private class startOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TimePickerPopupDialogTwoButton octDialog = new TimePickerPopupDialogTwoButton(friend_writeActivity.this, new TimePickerPopupDialogClickListener() {
                @Override
                public void onPositiveClick(int setHourValue, int setMinuteValue) {
                    Log.d(TAG, "onPositiveClick : " + setHourValue + "시 " + setMinuteValue + "분");
                    if(setMinuteValue < 10) start_time_btn.setText(setHourValue + ":0" + setMinuteValue);
                    else start_time_btn.setText(setHourValue + ":" + setMinuteValue);
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
                    if(setMinuteValue < 10) end_time_btn.setText(setHourValue + ":0" + setMinuteValue);
                    else end_time_btn.setText(setHourValue + ":" + setMinuteValue);
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