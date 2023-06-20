package com.example.eatswunee.bistro;

import static androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.recyclerView.MyBistroAdapter;
import com.example.eatswunee.bistro.recyclerView.MyViewPagerAdapter;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mankwon_bistroFragment extends Fragment {

    private View v;
    private RecyclerView mRecyclerView;
    private MyBistroAdapter bistroAdapter;
    private MyViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager;

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_total_bistro, container, false);

        init(3);

        mRecyclerView = v.findViewById(R.id.total_RecyclerView);
        viewPager = v.findViewById(R.id.view_pager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        return v;
    }

    private void init(long restaurantId) {

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        serviceApi.getData("gusia", restaurantId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();

                if(data.getHomeOrdersList() == null) {
                    viewPager.setVisibility(View.GONE);
                } else {
                    viewPagerAdapter = new MyViewPagerAdapter(data.getHomeOrdersList());
                    viewPager.setAdapter(viewPagerAdapter);
                    viewPager.setOrientation(ORIENTATION_HORIZONTAL);
                }

                bistroAdapter = new MyBistroAdapter(data.getMenusList());
                mRecyclerView.setAdapter(bistroAdapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}