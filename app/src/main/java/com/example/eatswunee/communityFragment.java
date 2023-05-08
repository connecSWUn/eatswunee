package com.example.eatswunee;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.eatswunee.bistro.recyclerView.MyCommunityAdapter;
import com.example.eatswunee.bistro.recyclerView.community_item;
import com.google.android.material.navigation.NavigationView;


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
        getData();

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

    /* 예시 */
    private void getData() {
        /* adapt data : example */
        community_item data = new community_item("밥 같이 먹을 사람 구해요", "구시아",
                "오후 12:30 - 오후 1:30",
                "2023.03.27 11:45", "F");
        adapter.addItem(data);
        data = new community_item("밥 먹을 사람", "50주년",
                "오후 12:00 - 오후 12:30",
                "2023.03.27 12:00", "T");
        adapter.addItem(data);
        data = new community_item("샌드위치 드실 분", "구시아",
                "오후 1:00 - 오후 1:30",
                "2023.03.28 12:00", "F");
        adapter.addItem(data);
        data = new community_item("떡볶이 먹고 싶다", "구시아",
                "오후 1:00 - 2:00",
                "2023.03.28 12:23", "D");
        adapter.addItem(data);
        data = new community_item("커피 마실 사람", "50주년",
                "오후 3:00 - 3:30",
                "2023.03.28 14:40", "D");
        adapter.addItem(data);
        data = new community_item("학식 드실 사람", "샬롬",
                "오후 3:30 - 4:00",
                "2023.03.28 15:23", "D");
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