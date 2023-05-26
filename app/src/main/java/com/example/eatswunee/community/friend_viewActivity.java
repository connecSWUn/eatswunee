package com.example.eatswunee.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatswunee.MainActivity;
import com.example.eatswunee.R;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class friend_viewActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    TextView title, spot, time, created_at, status, name, content;
    ImageView profile;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);

        title = findViewById(R.id.view_title);
        spot = findViewById(R.id.view_spot);
        time = findViewById(R.id.view_time);
        created_at = findViewById(R.id.view_createdAt);
        status = findViewById(R.id.view_status);
        name = findViewById(R.id.view_name);
        content = findViewById(R.id.view_content);
        profile = findViewById(R.id.view_profile);

        backBtn = findViewById(R.id.view_backBtn);
        backBtn.setOnClickListener(new backOnClickListener());

        Intent intent = getIntent();
        long postId = intent.getExtras().getLong("recruitId");

        init(postId);
    }

    private void init(long postId) {

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        serviceApi.getData(postId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();
                Log.d("retrofit", "Data fetch success");

                title.setText(data.getTitle());
                spot.setText(data.getSpot());
                time.setText(data.getStart_time() + "-" + data.getEnd_time());
                created_at.setText(data.getCreated_at());
                content.setText(data.getContent());
                name.setText(data.getWriters().getUser_name());

                if(data.getRecruit_status() == "ONGOING") {
                    status.setText("찾는 중...");
                    status.setBackgroundResource(R.drawable.community_state_finding);
                } else if (data.getRecruit_status() == "CONNECTING") {
                    status.setText("연락 중...");
                    status.setBackgroundResource(R.drawable.community_state_talking);
                } else if (data.getRecruit_status() == "COMPLETED") {
                    status.setText("구했어요!");
                    status.setBackgroundResource(R.drawable.community_state_done);
                }

                // 이미지 주소가 안 되어있음 : 수정 필요
                new DownloadFilesTask().execute(data.getWriters().getUser_profile_url());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            profile.setImageBitmap(result);
        }
    }

    private class backOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("selectedItem", "community");
            startActivity(intent);
        }
    }
}