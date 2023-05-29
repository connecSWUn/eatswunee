package com.example.eatswunee.bistro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.recyclerView.MyReviewAdapter;
import com.example.eatswunee.bistro.recyclerView.reviewItem;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;
import com.example.eatswunee.server.reviewRating;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    TextView menu_name, star_rate, review_num, review_title;
    TextView score5Cnt, score4Cnt, score3Cnt, score2Cnt, score1Cnt;
    ProgressBar score5, score4, score3, score2, score1;
    ImageView menu_image;

    private RecyclerView mRecyclerView;
    private MyReviewAdapter adapter;

    long menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Toolbar toolbar = (Toolbar) findViewById(R.id.review_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

        // 메뉴 이름 및 이미지 반영 : 이미지 반영 이전
        menu_name = findViewById(R.id.review_menu_name);
        // 평균 별점 : TextView
        star_rate = findViewById(R.id.review_star_rate);
        review_num = findViewById(R.id.review_num);
        review_title = findViewById(R.id.review_title);
        menu_image = findViewById(R.id.review_menu_image);

        score5Cnt = findViewById(R.id.five_star_num);
        score4Cnt = findViewById(R.id.four_star_num);
        score3Cnt = findViewById(R.id.three_star_num);
        score2Cnt = findViewById(R.id.two_star_num);
        score1Cnt = findViewById(R.id.one_star_num);

        score5 = findViewById(R.id.five_star);
        score4 = findViewById(R.id.four_star);
        score3 = findViewById(R.id.three_star);
        score2 = findViewById(R.id.two_star);
        score1 = findViewById(R.id.one_star);


        Intent intent = getIntent();
        menuId = intent.getExtras().getLong("menuId");

        init(menuId);

        // RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        /* initiate recyclerView */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuinfo_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.menuinfo_shopping_basket:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(long menuId) {

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        serviceApi.getData("menu", menuId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();

                menu_name.setText(data.getMenuName());
                //menu_image.setImageResource(data.getMenuImg());
                star_rate.setText(String.valueOf(data.getMenuAvgRating()));

                score5Cnt.setText(String.valueOf(data.getReviewRating().getScore5Cnt()));
                score4Cnt.setText(String.valueOf(data.getReviewRating().getScore4Cnt()));
                score3Cnt.setText(String.valueOf(data.getReviewRating().getScore3Cnt()));
                score2Cnt.setText(String.valueOf(data.getReviewRating().getScore2Cnt()));
                score1Cnt.setText(String.valueOf(data.getReviewRating().getScore1Cnt()));

                review_num.setText("리뷰" + data.getReviewCnt());
                review_title.setText("리뷰(" + data.getReviewCnt() + ")");

                // 이미지 주소가 안 되어있음 : 수정 필요
                // new menu_infoActivity().DownloadFilesTask().execute(data.getWriters().getUser_profile_url());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}