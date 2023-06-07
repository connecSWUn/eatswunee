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
import com.example.eatswunee.bistro.recyclerView.MyBistroAdapter;
import com.example.eatswunee.community.friend_viewActivity;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;
import com.example.eatswunee.server.sqlite.DBManager;
import com.example.eatswunee.shopbagActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class menu_infoActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    TextView RestaurantName, menuName, menuRating, menuPrice, menuCnt;
    Button putBtn, reviewBtn, cnt_plus, cnt_minus;
    ImageView menuImage;

    long menuId;
    String menu_image;

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
        menuCnt = findViewById(R.id.menu_info_pcs);
        putBtn = findViewById(R.id.put_btn);

        // 리뷰 확인 버튼 : 리뷰 페이지로 이동
        reviewBtn = findViewById(R.id.reviewBtn);

        // 수량 변경 버튼
        cnt_plus = findViewById(R.id.plusBtn);
        cnt_minus = findViewById(R.id.minBtn);

        Intent intent = getIntent();
        menuId = intent.getExtras().getLong("menuId");
        menu_image = intent.getExtras().getString("menuImage");

        init(menuId);

        reviewBtn.setOnClickListener(new reviewOnClickListener());
        putBtn.setOnClickListener(new putOnClickListener());

        cnt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cnt = Integer.parseInt((String) menuCnt.getText());
                int price = Integer.parseInt((String) menuPrice.getText());

                menuCnt.setText(String.valueOf(cnt + 1));
                putBtn.setText((price * (cnt + 1)) + "원 담기");
            }
        });

        cnt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cnt = Integer.parseInt((String) menuCnt.getText());
                int price = Integer.parseInt((String) menuPrice.getText());

                if(cnt == 1) Toast.makeText(menu_infoActivity.this, "최소 수량입니다.", Toast.LENGTH_SHORT).show();
                else {
                    menuCnt.setText(String.valueOf(cnt - 1));
                    putBtn.setText((price * (cnt - 1)) + "원 담기");
                }
            }
        });
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
                putBtn.setText(data.getMenuPrice()  + "원 담기");

                new ImageLoadTask(data.getMenuImg(), menuImage).execute();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
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

    private class putOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String res_name = RestaurantName.getText().toString();
            String menu_name = menuName.getText().toString();
            int menu_price = Integer.parseInt((String) menuPrice.getText());
            int menu_cnt = Integer.parseInt((String) menuCnt.getText());

            // DB 객체 생성
            DBManager dbManager = new DBManager(menu_infoActivity.this);
            // DB에 저장하기
            dbManager.addBag(menuId, menu_name, menu_image, menu_price, res_name, menu_cnt);

            Intent intent = new Intent(menu_infoActivity.this, shopbagActivity.class);
            startActivity(intent);
            finish();
        }
    }
}