package com.example.eatswunee.mypage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.review_writeActivity;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.menus;
import com.example.eatswunee.server.orders;
import com.example.eatswunee.server.reviews;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private List<orders> ordersList;
    Context context;

    public MyListAdapter(Context context, List<orders> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_list_one, parent, false);
        return new MyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        orders item = ordersList.get(position);
        holder.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, review_writeActivity.class);
                intent.putExtra("restaurant_name", item.getRestaurantName());
                intent.putExtra("menu_name", item.getMenuName());
                intent.putExtra("menu_id", item.getOrderMenuId());
                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return ordersList.size(); }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView bistro_name, menu_name, price, cnt, total_price, date;
        Button reviewBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bistro_name = (TextView) itemView.findViewById(R.id.list_bistro_name);
            menu_name = (TextView) itemView.findViewById(R.id.list_menu_name);
            price = (TextView) itemView.findViewById(R.id.list_price);
            cnt = (TextView) itemView.findViewById(R.id.list_cnt);
            total_price = (TextView) itemView.findViewById(R.id.list_total_price);
            date = (TextView) itemView.findViewById(R.id.list_date);
            reviewBtn = (Button) itemView.findViewById(R.id.list_review_btn);
        }

        void setItem(orders item) {
            bistro_name.setText("[" + item.getRestaurantName() + "]");
            menu_name.setText(item.getMenuName());
            price.setText(item.getMenuPrice() + "원");
            cnt.setText(item.getMenuCnt() + "개");
            total_price.setText(item.getMenuTotalPrice() + "원");
            date.setText(item.getOrderCreatedAt());

            if(item.isUserWriteReview() == true) {
                reviewBtn.setEnabled(false);
                reviewBtn.setBackgroundResource(R.drawable.order_list_btn_unclickable);
                reviewBtn.setText("리뷰 작성 완료");
            } else {
                reviewBtn.setEnabled(true);
            }
        }
    }
}
