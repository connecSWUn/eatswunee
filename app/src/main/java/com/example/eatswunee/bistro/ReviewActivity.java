package com.example.eatswunee.bistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatswunee.R;

public class ReviewActivity extends AppCompatActivity {

    TextView menu_name;
    ProgressBar five, four, three, two, one;
    TextView five_num, four_num, three_num, two_num, one_num;
    TextView star_rate;
    TextView review_num;

    // Intent로 전송받는 정보
    String bistro, menu, star, price_;

    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // 메뉴 이름 및 이미지 반영 : 이미지 반영 이전
        menu_name = findViewById(R.id.review_menu_name);

        // 평균 별점
        star_rate = findViewById(R.id.review_star_rate);

        // 뒤로가기 버튼
        backBtn = findViewById(R.id.review_backBtn);

        Intent intent = getIntent();
        bistro = intent.getStringExtra("bistro_name");
        menu = intent.getStringExtra("menu_name");
        star = intent.getStringExtra("star_rate");
        price_ = intent.getStringExtra("price");

        menu_name.setText(menu);
        star_rate.setText(star);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewActivity.this, menu_infoActivity.class);
                intent.putExtra("bistro_name", bistro);
                intent.putExtra("menu_name", menu);
                intent.putExtra("star_rate", star);
                intent.putExtra("price", price_);
                startActivity(intent);
            }
        });
    }
}