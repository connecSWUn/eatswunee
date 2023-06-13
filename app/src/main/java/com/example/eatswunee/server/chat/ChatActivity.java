package com.example.eatswunee.server.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eatswunee.R;
import com.example.eatswunee.mypage.MyListAdapter;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyListAdapter adapter;

    private RetrofitClient retrofitClient;
    private OkHttpClient client;
    private WebSocket ws;
    private ServiceApi serviceApi;
    private long postId = 0;
    private String user_id;
    private String messageType;

    TextView title, date, spot, time, status, nickname, start_message;
    EditText message;
    ImageButton sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

        title = findViewById(R.id.chat_title);
        date = findViewById(R.id.chat_date);
        spot = findViewById(R.id.chat_spot);
        time = findViewById(R.id.chat_time);
        status = findViewById(R.id.chat_status);
        nickname = findViewById(R.id.nickname);
        start_message = findViewById(R.id.start_message);

        message = findViewById(R.id.chat_box);
        sendBtn = findViewById(R.id.send_btn);

        Intent intent = getIntent();
        postId = intent.getExtras().getLong("recruitId");
        messageType = intent.getExtras().getString("messageType");

        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("ws://43.201.201.163:8080/ws/chat")
                .build();
        WebSocketListener listener = new WebSocketListener();
        ws = client.newWebSocket(request, listener);

        // RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.addItemDecoration(new RecyclerViewDecoration(50));

        sendBtn.setOnClickListener(new sendOnClickListener());

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

        /* initiate recyclerView */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void init(long postId) {

        serviceApi.enterChat(postId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();

                title.setText(data.getRecruit_title());
                date.setText(data.getRecruit_created_at());
                spot.setText(data.getRecruit_spot());
                time.setText(data.getRecruit_start_time() + "-" + data.getRecruit_end_time());
                status.setText(data.getRecruit_status());
                nickname.setText(data.getSender_name());
                start_message.setText(data.getSender_name() + "님과의 대화를 시작합니다.");

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
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private final class WebSocketListener extends okhttp3.WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull okhttp3.Response response) {
            super.onOpen(webSocket, response);

        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            super.onMessage(webSocket, text);
        }

        @Override
        public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            super.onClosing(webSocket, code, reason);
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            webSocket.cancel();
            Log.d("WebSocketConnection", "Closing : " + code + " / " + reason);
        }

        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable okhttp3.Response response) {
            super.onFailure(webSocket, t, response);
            Log.d("WebSocketConnection", "Error : " + t.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

        private final int divHeight;

        public RecyclerViewDecoration(int divHeight)
        {
            this.divHeight = divHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = divHeight;
        }
    }

    private class sendOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            JSONObject object = new JSONObject();
            try {
                object.put("messageType", messageType);
                object.put("chatRoomId", Long.valueOf(user_id + "0" + postId));
                object.put("senderId", Long.valueOf(user_id));
                object.put("message", message.getText());

                ws.send(object.toString());

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}