package com.example.eatswunee;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.eatswunee.mypage.MyCommunityAdapter;
import com.example.eatswunee.community.community_item;
import com.example.eatswunee.server.CommunityDto;
import com.example.eatswunee.server.CommunityResponse;
import com.example.eatswunee.server.ServiceApi;

import java.util.List;

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

    // baseUrl은 반드시 '/'로 마무리되어야 함
    private String baseUrl = "http://43.201.201.163:8080/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi api = retrofit.create(ServiceApi.class);
        api.getData("ALL")
                .enqueue(new Callback<CommunityResponse>() {
                    @Override
                    public void onResponse(Call<CommunityResponse> call, Response<CommunityResponse> response) {
                        if(response.isSuccessful()) {
                            CommunityResponse communityResponse = response.body();
                            Log.d("##", "Successed!, Result \n" + communityResponse.toString());

                            List<CommunityDto> list = new CommunityResponse().getPost().getPost();
                            Log.d("MY", list.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommunityResponse> call, Throwable t) {

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

    private void setData(int recruitId, String createdAt, String title, String status,
                         String spot, String startTime, String endTime) {
        community_item data = new community_item(title, spot, startTime + " - " + endTime,
                createdAt, status);
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