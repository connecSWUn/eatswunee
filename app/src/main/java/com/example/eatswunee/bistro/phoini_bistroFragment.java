package com.example.eatswunee.bistro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.recyclerView.MyBistroAdapter;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class phoini_bistroFragment extends Fragment {

    private View v;
    private RecyclerView mRecyclerView;
    private MyBistroAdapter adapter;

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_total_bistro, container, false);

        init(1);

        mRecyclerView = v.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        return v;
    }

    private void init(long restaurantId) {

        /*
        serviceApi.getData("gusia", restaurantId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                Data data = result.getData();
                adapter = new MyBistroAdapter(data.getMenusList());

                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
            }
        });
         */

    }
}