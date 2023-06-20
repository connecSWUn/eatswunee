package com.example.eatswunee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatswunee.bistro.order_waitingActivity;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;
import com.example.eatswunee.server.menuListDto;
import com.example.eatswunee.server.orderMenuList;
import com.example.eatswunee.server.sqlite.DBManager;
import com.example.eatswunee.server.sqlite.shop_bag;
import com.example.eatswunee.server.sqlite.shopbagAdapter;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class shopbagActivity extends AppCompatActivity {

    private DBManager dbManager;
    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;
    ArrayList<shop_bag> bags = new ArrayList<>();
    RecyclerView recyclerView;
    TextView payment_btn;
    shopbagAdapter adapter;
    TextView noDataText, total_cnt, total_price;

    int cnt_sum = 0;
    int price_sum = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopbag);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        // 데이터 유무 텍스트
        noDataText = findViewById(R.id.noDataText);
        payment_btn = findViewById(R.id.pay_btn);
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

        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbManager.readAllData();

                if(cursor.getCount() == 0) {
                    Toast.makeText(shopbagActivity.this, "주문 항목이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject object = new JSONObject();
                    JSONArray array = new JSONArray();

                    try {

                        while (cursor.moveToNext()) {
                            JSONObject objectChild = new JSONObject();

                            int menu_id = cursor.getInt(0);
                            int menu_cnt = cursor.getInt(5);

                            objectChild.put("menuId", menu_id);
                            objectChild.put("menuCnt", menu_cnt);

                            array.put(objectChild);
                        }
                        object.put("orderMenuList", array);

                        serviceApi.postOrder(object).enqueue(new Callback<Result>() {
                            @Override
                            public void onResponse(Call<Result> call, Response<Result> response) {
                                Log.d("order", String.valueOf(response.isSuccessful()));
                            }

                            @Override
                            public void onFailure(Call<Result> call, Throwable t) {

                            }
                        });

                        System.out.println(object);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    dbManager.deleteAllData();

                    Intent intent = new Intent(shopbagActivity.this, order_waitingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
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