package com.example.eatswunee.bistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatswunee.MainActivity;
import com.example.eatswunee.R;

public class menu_infoActivity extends AppCompatActivity {

    TextView bistro_name, menu_name, star_rate, price;
    ImageButton backBtn;
    Button putBtn, reviewBtn;

    String bistro, menu, star, price_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);

        // 메뉴 따라 텍스트 반영
        bistro_name = findViewById(R.id.info_bistro_name);
        menu_name = findViewById(R.id.info_menu_name);
        star_rate = findViewById(R.id.info_star_rate);
        price = findViewById(R.id.info_price);
        putBtn = findViewById(R.id.putBtn);

        // 뒤로가기 버튼 : 메인 액티비티로 귀환
        backBtn = findViewById(R.id.backBtn);
        // 리뷰 확인 버튼 : 리뷰 페이지로 이동
        reviewBtn = findViewById(R.id.reviewBtn);


        /** Fragment에서 전달받은 메뉴 정보 반영
         * - 결제 시 전송해야 할 데이터
         * bistro_name (식당 이름) / menu_name (메뉴 이름) / star_rate (평균 별점) / price (가격)
         */
        Intent intent = getIntent();
        bistro = intent.getStringExtra("bistro_name");
        menu = intent.getStringExtra("menu_name");
        star = intent.getStringExtra("star_rate");
        price_ = intent.getStringExtra("price");


        bistro_name.setText(bistro);
        menu_name.setText(menu);
        star_rate.setText(star);
        price.setText(price_);
        putBtn.setText(price_ + " 담기");


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_infoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_infoActivity.this, ReviewActivity.class);
                intent.putExtra("bistro_name", bistro);
                intent.putExtra("menu_name", menu);
                intent.putExtra("star_rate", star);
                intent.putExtra("price", price_);
                startActivity(intent);
            }
        });
    }
}