package com.example.eatswunee.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private ArrayList<list_item> listItems = new ArrayList<>();

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_list, parent, false);
        return new MyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        holder.onBind(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void addItem(list_item data) { listItems.add(data); }

    public list_item getItem(int position) {
        return listItems.get(position); // 아이템 가져오기
    }

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
