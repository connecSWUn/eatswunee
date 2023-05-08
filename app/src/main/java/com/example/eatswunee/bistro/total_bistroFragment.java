package com.example.eatswunee.bistro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.recyclerView.MyBistroAdapter;
import com.example.eatswunee.bistro.recyclerView.menu_item;


public class total_bistroFragment extends Fragment {

    private View v;
    private MyBistroAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_total_bistro, container, false);

        adapter = new MyBistroAdapter();

        adapter.setOnItemClickListener(new MyBistroAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyBistroAdapter.ViewHolder holder, View v, int pos) {
                menu_item item = adapter.getItem(pos);

                // 화면 전환 구현, intent 전달 필요
                Intent intent = new Intent(getActivity(), menu_infoActivity.class);
                // 사진은 따로 옮겨야 함
                intent.putExtra("bistro_name",item.getBistro_name());
                intent.putExtra("menu_name", item.getMenu_name());
                intent.putExtra("star_rate", item.getStar_rate());
                intent.putExtra("price", item.getPrice());
                startActivity(intent);
            }
        });

        init();
        getData();

        return v;
    }

    private void init() {
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    /** 예시 **/
    private void getData() {
        menu_item data = new menu_item(R.drawable.baseline_image_not_supported_24,
                "포아이니", "3.5", "차돌양지쌀국수", "6,500원");
        adapter.addItem(data);
        data = new menu_item(R.drawable.baseline_image_not_supported_24,
                "분식대첩", "3.0", "김치볶음밥", "5,900원");
        adapter.addItem(data);
        data = new menu_item(R.drawable.baseline_image_not_supported_24,
                "만권화밥", "4.5", "매운닭갈비덮밥", "6,500원");
        adapter.addItem(data);
        data = new menu_item(R.drawable.baseline_image_not_supported_24,
                "최고당돈가스", "3.5", "수제생등심돈가스", "6,400원");
        adapter.addItem(data);
        data = new menu_item(R.drawable.baseline_image_not_supported_24,
                "포아이니", "3.5", "차돌양지쌀국수", "6,900원");
        adapter.addItem(data);
        data = new menu_item(R.drawable.baseline_image_not_supported_24,
                "분식대첩", "4.5", "라면", "3,500원");
        adapter.addItem(data);
    }
}