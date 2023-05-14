package com.example.eatswunee;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.eatswunee.mypage.MyCommunityAdapter;
import com.example.eatswunee.mypage.community_item;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.ServiceApi;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class communityFragment extends Fragment {

    Button total, gusia, nuri, fiftieth, shalom;
    View v;

    private RecyclerView mRecyclerView;
    private MyCommunityAdapter adapter;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-43-201-201-163.ap-northeast-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        serviceApi.getData("10003040", "all").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if(response.isSuccessful()) {
                    Result data = response.body();
                    int recruitId = data.getRecruitId();
                    LocalDateTime createdAt = data.getCreatedAt();
                    String title = data.getTitle();
                    String status = data.getStatus();
                    String spot = data.getSpot();
                    LocalDateTime startTime = data.getStartTime();
                    LocalDateTime endTime = data.getEndTime();

                    setData(recruitId, createdAt, title, status, spot, startTime, endTime);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_community, container, false);

        /* initiate adapter */
        adapter = new MyCommunityAdapter();

        total = v.findViewById(R.id.community_totalBtn);
        gusia = v.findViewById(R.id.community_gusia);
        nuri = v.findViewById(R.id.community_nuri);
        fiftieth = v.findViewById(R.id.community_50th);
        shalom = v.findViewById(R.id.community_shalom);

        total.setSelected(true);

        init();

        total.setOnClickListener(new totalOnClickListener());
        gusia.setOnClickListener(new gusiaOnClickListener());
        nuri.setOnClickListener(new nuriOnClickListener());
        fiftieth.setOnClickListener(new fiftiethOnClickListener());
        shalom.setOnClickListener(new shalomOnClickListener());


        return v;
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
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);
        mRecyclerView.addItemDecoration(new RecyclerViewDecoration(20));

        /* initiate recyclerView */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        mRecyclerView.setAdapter(adapter);
    }

    private void setData(int recruitId, LocalDateTime createdAt, String title, String status,
                         String spot, LocalDateTime startTime, LocalDateTime endTime) {
        community_item data = new community_item(title, spot, startTime + " - " + endTime,
                createdAt.toString(), status);
        adapter.addItem(data);
    }

    private class totalOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            total.setSelected(true);
            gusia.setSelected(false);
            nuri.setSelected(false);
            fiftieth.setSelected(false);
            shalom.setSelected(false);
        }
    }

    private class gusiaOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            total.setSelected(false);
            gusia.setSelected(true);
            nuri.setSelected(false);
            fiftieth.setSelected(false);
            shalom.setSelected(false);
        }
    }

    private class nuriOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            total.setSelected(false);
            gusia.setSelected(false);
            nuri.setSelected(true);
            fiftieth.setSelected(false);
            shalom.setSelected(false);
        }
    }

    private class fiftiethOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            total.setSelected(false);
            gusia.setSelected(false);
            nuri.setSelected(false);
            fiftieth.setSelected(true);
            shalom.setSelected(false);
        }
    }

    private class shalomOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            total.setSelected(false);
            gusia.setSelected(false);
            nuri.setSelected(false);
            fiftieth.setSelected(false);
            shalom.setSelected(true);
        }
    }
}