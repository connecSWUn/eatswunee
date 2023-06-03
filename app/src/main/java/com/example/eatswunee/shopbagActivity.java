package com.example.eatswunee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eatswunee.server.sqlite.DBManager;
import com.example.eatswunee.server.sqlite.shop_bag;
import com.example.eatswunee.server.sqlite.shopbagAdapter;

import java.util.ArrayList;

public class shopbagActivity extends AppCompatActivity {

    private DBManager dbManager;
    ArrayList<shop_bag> bags = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayout payment_btn;
    shopbagAdapter adapter;
    TextView noDataText, total_cnt, total_price;

    int cnt_sum = 0;
    int price_sum = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopbag);

        // 데이터 유무 텍스트
        noDataText = findViewById(R.id.noDataText);
        payment_btn = findViewById(R.id.payment_btn);
        total_cnt = findViewById(R.id.total_cnt);
        total_price = findViewById(R.id.total_price);

        Toolbar toolbar = (Toolbar) findViewById(R.id.shopbag_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

        recyclerView = findViewById(R.id.shopbag_RecyclerView);
        adapter = new shopbagAdapter(shopbagActivity.this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        dbManager = new DBManager(shopbagActivity.this);
        storeDataInArrays();

        total_cnt.setText(String.valueOf(cnt_sum));
        total_price.setText(price_sum + "원");
    }

    /** 데이터 가져오기 **/
    void storeDataInArrays(){
        Cursor cursor = dbManager.readAllData();

        if(cursor.getCount() == 0) {
            noDataText.setVisibility(noDataText.VISIBLE);
        } else {

            noDataText.setVisibility(noDataText.GONE);

            while(cursor.moveToNext()) {

                shop_bag bag = new shop_bag(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5));


                cnt_sum = cnt_sum + cursor.getInt(5);
                price_sum = price_sum + (cursor.getInt(3) * cursor.getInt(5));

                // 데이터 등록
                bags.add(bag);
                adapter.addItem(bag);

                // 적용
                adapter.notifyDataSetChanged();
            }
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
}