package com.example.eatswunee.bistro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.recyclerView.MyReviewAdapter;
import com.example.eatswunee.bistro.recyclerView.reviewItem;

public class ReviewActivity extends AppCompatActivity {

    TextView menu_name;
    TextView star_rate;

    private RecyclerView mRecyclerView;
    private MyReviewAdapter adapter;

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

        /* initiate adapter */
        adapter = new MyReviewAdapter();

        Intent intent = getIntent();
        bistro = intent.getStringExtra("bistro_name");
        menu = intent.getStringExtra("menu_name");
        star = intent.getStringExtra("star_rate");
        price_ = intent.getStringExtra("price");

        menu_name.setText(menu);
        star_rate.setText(star);

        init();
        getData();

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

    private void init() {
        // RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.addItemDecoration(new RecyclerViewDecoration(15));

        /* initiate recyclerView */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mRecyclerView.setAdapter(adapter);
    }

    /* 예시 */
    private void getData() {
        /* adapt data : example */
        reviewItem data = new reviewItem(R.drawable.review_profile, "user1",
                "정말 맛있었어요!!",
                "2023.04.13", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user1",
                "정말 맛있었어요!!",
                "2023.04.13", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user1",
                "정말 맛있었어요!!",
                "2023.04.13", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user1",
                "정말 맛있었어요!!",
                "2023.04.13", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user1",
                "정말 맛있었어요!!",
                "2023.04.13", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user2",
                "두 줄을 작성하려면 말을 얼마나 적어야 할까 제대로 들어가는지 확인해야 할 것 같아서 쓰는 리뷰",
                "2023.04.12", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user3",
                "진짜 너무 배고파요 집에 가고 싶어요",
                "2023.04.11", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user4",
                "훌륭합니다 맛 양 전부 좋습니다",
                "2023.04.10", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user5",
                "맛, 양 다 너무 좋았어요!!! 다음에도 또 시켜 먹어야겠네요~",
                "2023.04.09", 5);
        adapter.addItem(data);
        data = new reviewItem(R.drawable.review_profile, "user6",
                "두 번째 시겨서 먹었는데 \n 짱 맛집 \n 진짜 맛있어요 \n 꼭 시켜서 드셔 보세요~",
                "2023.04.08", 5);
        adapter.addItem(data);
    }
}