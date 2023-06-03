package com.example.eatswunee.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.orders;
import com.example.eatswunee.server.reviews;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private Data data;

    public MyListAdapter(Data data) { this.data = data; }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_review_photo, parent, false);
        return new MyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() { /*return data.size();*/ return 0; }

    // 이 밑으로 수정 필요
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView bistro_name;
        TextView menu_name;
        TextView price;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bistro_name = (TextView) itemView.findViewById(R.id.list_bistro_name);
            menu_name = (TextView) itemView.findViewById(R.id.list_menu_name);
            price = (TextView) itemView.findViewById(R.id.list_price);
            date = (TextView) itemView.findViewById(R.id.list_date);
        }

        void onBind(list_item item) {
            bistro_name.setText("[" + item.getBistro_name() + "]");
            menu_name.setText(item.getMenu_name());
            price.setText(item.getPrice());
            date.setText(item.getDate());
        }
    }
}
