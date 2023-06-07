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

    private List<menus> menusList;
    Context context;

    public MyListAdapter(List<menus> menusList) { this.menusList = menusList; }
    public MyListAdapter(Context context) { this.context = context; }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_list_one, parent, false);
        return new MyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        menus item = menusList.get(position);
        holder.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((Activity)context), review_writeActivity.class);
                ((Activity)context).startActivity(intent);
            }
        });

        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return menusList.size(); }


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

        void setItem(menus item) {
            bistro_name.setText(item.getRestaurant_name());
            menu_name.setText(item.getMenu_name());
            price.setText(item.getMenu_price());
            cnt.setText(item.getMenu_cnt());
            total_price.setText(item.getMenu_total_price());
            date.setText(item.getOrder_created_at());

            if(item.getIs_user_write_review() == true) {
                reviewBtn.setEnabled(false);
                reviewBtn.setText("리뷰 작성 완료");
            } else {
                reviewBtn.setEnabled(true);
            }
        }
    }
}
