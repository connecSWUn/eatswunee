package com.example.eatswunee.mypage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eatswunee.MainActivity;
import com.example.eatswunee.R;
import com.example.eatswunee.bistro.ReviewActivity;
import com.example.eatswunee.bistro.recyclerView.MyReviewAdapter;
import com.example.eatswunee.bistro.recyclerView.reviewItem;

public class order_listActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.order_list_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

        /* initiate adapter */
        adapter = new MyListAdapter();

        init();
        getData();
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

    private void init() {
        // RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.addItemDecoration(new RecyclerViewDecoration(50));

        /* initiate recyclerView */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mRecyclerView.setAdapter(adapter);
    }

    /* 예시 */
    private void getData() {
        /* adapt data : example */
        list_item data = new list_item("만권화밥", "매운닭갈비덮밥","6,500원", "2023.03.31");
        adapter.addItem(data);
        data = new list_item("포아이니", "차돌양지쌀국수", "6,500원", "2023.03.05");
        adapter.addItem(data);
        data = new list_item("분식대첩", "김치볶음밥", "5,900원", "2023.03.01");
        adapter.addItem(data);
    }
}