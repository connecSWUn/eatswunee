package com.example.eatswunee.community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatswunee.server.chat.ChatActivity;
import com.example.eatswunee.R;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;
import com.example.eatswunee.server.chat.chat_listActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class friend_viewActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;
    boolean isWriter = false;
    private long postId = 0;
    private String user_id;

    TextView title, spot, time, created_at, status, name, content;
    LinearLayout background, inside;
    ImageView profile;
    Button chat_btn, chat_list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        Toolbar toolbar = (Toolbar) findViewById(R.id.friend_view_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

        title = findViewById(R.id.view_title);
        spot = findViewById(R.id.view_spot);
        time = findViewById(R.id.view_time);
        created_at = findViewById(R.id.view_createdAt);
        status = findViewById(R.id.view_status);
        name = findViewById(R.id.view_name);
        content = findViewById(R.id.view_content);
        profile = findViewById(R.id.view_profile);
        chat_btn = findViewById(R.id.chat_btn);
        chat_list_btn = findViewById(R.id.chat_list_btn);

        background = findViewById(R.id.friend_view_bottom_back);
        inside = findViewById(R.id.friend_view_bottom_in);
        chat_btn.setOnClickListener(new chatOnClickListener());

        Intent intent = getIntent();
        postId = intent.getExtras().getLong("recruitId");

        serviceApi.getProfile().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();

                user_id = data.getUser_id();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        init(postId);

        chat_list_btn.setOnClickListener(new chatListOnClickListener());
    }

    private void init(long postId) {

        serviceApi.getData("recruit", postId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();
                Log.d("retrofit", "Data fetch success");

                boolean user_is_writer = data.isUser_is_writer();

                if(user_is_writer) {
                    chat_list_btn.setVisibility(View.VISIBLE);
                    background.setVisibility(View.GONE);

                    invalidateOptionsMenu();
                }

                title.setText(data.getTitle());
                spot.setText(data.getSpot());
                time.setText(data.getStart_time() + " - " + data.getEnd_time());
                created_at.setText(data.getCreated_at());
                content.setText(data.getContent());
                name.setText(data.getWriters().getUser_name());

                if(data.getRecruit_status() == "ONGOING") {
                    status.setText("찾는 중이군요!");
                    status.setBackgroundResource(R.drawable.com_finding_chat_theme);
                    background.setBackgroundColor(getResources().getColor(R.color.finding));
                    inside.setBackgroundResource(R.drawable.com_finding_theme_bottom_s);
                } else if (data.getRecruit_status() == "CONNECTING") {
                    status.setText("연락 중이군요!");
                    status.setBackgroundResource(R.drawable.com_talking_chat_theme);
                    background.setBackgroundColor(getResources().getColor(R.color.talking));
                    inside.setBackgroundResource(R.drawable.com_talking_theme_bottom_s);
                } else if (data.getRecruit_status() == "COMPLETED") {
                    status.setText("이미 구했군요!");
                    status.setBackgroundResource(R.drawable.com_done_chat_theme);
                    background.setBackgroundColor(getResources().getColor(R.color.done));
                    inside.setBackgroundResource(R.drawable.com_done_theme_bottom_s);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(chat_list_btn.getVisibility() == View.VISIBLE) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_article, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.edit: {

                Intent intent = new Intent(friend_viewActivity.this, friend_writeActivity.class);
                intent.putExtra("edit", true);
                intent.putExtra("postId", postId);
                startActivity(intent);
                return true;
            }
            case R.id.delete: { // 게시글 삭제
                serviceApi.postDelete(postId).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(friend_viewActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
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

    private class chatOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            serviceApi.getExist(postId).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Result result = response.body();
                    Data data = result.getData();

                    if (data.isExist_chatroom() == true) {
                        Intent intent = new Intent(friend_viewActivity.this, ChatActivity.class);
                        intent.putExtra("chatRoomId", Long.valueOf(user_id + "0" + postId));
                        startActivity(intent);
                    } else if (data.isExist_chatroom() == false) {
                        serviceApi.makeChat(postId).enqueue(new Callback<Result>() {
                            @Override
                            public void onResponse(Call<Result> call, Response<Result> response) {
                                Result result = response.body();
                                Data data = result.getData();

                                long chat_id = data.getChat_room_id();
                                Intent intent = new Intent(friend_viewActivity.this, ChatActivity.class);
                                intent.putExtra("chatRoomId", chat_id);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Result> call, Throwable t) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }
            });
        }
    }

    private class chatListOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(friend_viewActivity.this, chat_listActivity.class);
            startActivity(intent);
        }
    }
}