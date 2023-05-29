package com.example.eatswunee.bistro;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatswunee.MainActivity;
import com.example.eatswunee.R;
import com.example.eatswunee.community.friend_viewActivity;
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

public class menu_infoActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    TextView RestaurantName, menuName, menuRating, menuPrice;
    Button putBtn, reviewBtn;
    ImageView menuImage;

    long menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_info_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);


        // 메뉴 따라 텍스트 반영
        RestaurantName = findViewById(R.id.info_bistro_name);
        menuName = findViewById(R.id.info_menu_name);
        menuRating = findViewById(R.id.info_star_rate);
        menuPrice = findViewById(R.id.info_price);
        putBtn = findViewById(R.id.put_btn);

        // 리뷰 확인 버튼 : 리뷰 페이지로 이동
        reviewBtn = findViewById(R.id.reviewBtn);

        Intent intent = getIntent();
        menuId = intent.getExtras().getLong("menuId");

        init(menuId);

        reviewBtn.setOnClickListener(new reviewOnClickListener());
    }

    private void init(long menuId) {

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        serviceApi.getData(menuId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();

                RestaurantName.setText(data.getRestaurantName());
                menuName.setText(data.getMenuName());
                menuRating.setText(String.valueOf(data.getMenuRating()));
                menuPrice.setText(String.valueOf(data.getMenuPrice()));
                putBtn.setText(data.getMenuPrice() + "원 담기");

                // 이미지 주소가 안 되어있음 : 수정 필요
                // new menu_infoActivity().DownloadFilesTask().execute(data.getWriters().getUser_profile_url());
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
            //profile.setImageBitmap(result);
        }
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

    private class reviewOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(menu_infoActivity.this, ReviewActivity.class);
            intent.putExtra("menuId", menuId);
            startActivity(intent);
        }
    }
}