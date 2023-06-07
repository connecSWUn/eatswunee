package com.example.eatswunee;

import android.content.Intent;
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

import com.example.eatswunee.community.MyCommunityAdapter;
import com.example.eatswunee.community.friend_writeActivity;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class communityFragment extends Fragment {
    Button total, gusia, nuri, fiftieth, shalom, gyo;
    Button writeBtn;
    View v;

    private RecyclerView mRecyclerView;
    private MyCommunityAdapter adapter;

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_community, container, false);

        total = v.findViewById(R.id.community_totalBtn);
        gusia = v.findViewById(R.id.community_gusia);
        nuri = v.findViewById(R.id.community_nuri);
        fiftieth = v.findViewById(R.id.community_50th);
        shalom = v.findViewById(R.id.community_shalom);
        gyo = v.findViewById(R.id.community_gyo);
        writeBtn = v.findViewById(R.id.write_button);

        /* 초기 세팅
         * 어플리케이션 실행 시 커뮤니티 화면 기본 선택 버튼 : 전체 버튼
         */
        total.setSelected(true);
        gusia.setSelected(false);
        nuri.setSelected(false);
        fiftieth.setSelected(false);
        shalom.setSelected(false);
        gyo.setSelected(false);

        init("ALL");

        /* 커뮤니티 버튼 리스너 */
        total.setOnClickListener(new totalOnClickListener());
        gusia.setOnClickListener(new gusiaOnClickListener());
        nuri.setOnClickListener(new nuriOnClickListener());
        fiftieth.setOnClickListener(new fiftiethOnClickListener());
        shalom.setOnClickListener(new shalomOnClickListener());
        gyo.setOnClickListener(new gyoOnClickListener());

        /* 글 쓰기 버튼 리스너 */
        writeBtn.setOnClickListener(new writeOnClickListener());

        /* RecyclerView */
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView2);
        mRecyclerView.addItemDecoration(new RecyclerViewDecoration(20));

        /* initiate recyclerView */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        return v;
    }

    public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

        private final int divHeight;

        public RecyclerViewDecoration(int divHeight) {
            this.divHeight = divHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = divHeight;
        }
    }

    private void init(String category) {

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        serviceApi.getData(category).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();
                Log.d("retrofit", "Data fetch success");
                /* initiate adapter */
                adapter = new MyCommunityAdapter(data.getPostList());

                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private class writeOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), friend_writeActivity.class);
            intent.putExtra("edit", false);
            startActivity(intent);
        }
    }

    private class totalOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            total.setSelected(true);
            gusia.setSelected(false);
            nuri.setSelected(false);
            fiftieth.setSelected(false);
            shalom.setSelected(false);
            gyo.setSelected(false);

            init("ALL");
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
            gyo.setSelected(false);

            init("gusia");
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
            gyo.setSelected(false);

            init("nuri");
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
            gyo.setSelected(false);

            init("fiftieth");
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
            gyo.setSelected(false);

            init("shalom");
        }
    }

    private class gyoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            total.setSelected(false);
            gusia.setSelected(false);
            nuri.setSelected(false);
            fiftieth.setSelected(false);
            shalom.setSelected(false);
            gyo.setSelected(true);
        }
    }
}