package com.example.eatswunee.bistro.recyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.server.homeOrders;
import com.example.eatswunee.server.orders;

import java.util.List;

public class MyViewPagerAdapter extends RecyclerView.Adapter<MyViewPagerAdapter.ViewHolder> {

    private List<homeOrders> ordersList;

    public MyViewPagerAdapter(List<homeOrders> ordersList) { this.ordersList = ordersList; }

    @NonNull
    @Override
    public MyViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewPagerAdapter.ViewHolder holder, int position) {
        homeOrders item = ordersList.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView order_num, waiting_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            order_num = (TextView) itemView.findViewById(R.id.order_num);
            waiting_time = (TextView) itemView.findViewById(R.id.waiting_time);
        }

        void setItem(homeOrders item) {
            order_num.setText(String.valueOf(item.getOrderNum()));
            waiting_time.setText(item.getExpectedWatingTime() + " 분");
        }
    }
}
