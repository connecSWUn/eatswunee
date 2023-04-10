package com.example.eatswunee.bistro.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    // OnClickListener Custom --------------------------------------
    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, View v, int pos);
    }

    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    // OnClickListener Custom --------------------------------------

    private ArrayList<menu_item> mMenu_item = new ArrayList<>();

    public interface OnItemClickEventListener {
        void onItemClick(ViewHolder holder, View a_view, int a_position);
    }

    private OnItemClickEventListener mItemClickListener;

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bistro_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mMenu_item.get(position));
    }

    @Override
    public int getItemCount() {
        return mMenu_item.size();
    }

    public void addItem(menu_item data) {
        mMenu_item.add(data);
    }

    public menu_item getItem(int position) {
        return mMenu_item.get(position); // 아이템 가져오기
    }

    public void setOnItemClickListener(OnItemClickEventListener a_listener) {
        mItemClickListener = a_listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView menu_image;
        TextView bistro_name;
        TextView star_rate;
        TextView menu_name;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menu_image = (ImageView) itemView.findViewById(R.id.menu_image);
            bistro_name = (TextView) itemView.findViewById(R.id.bistro_name);
            star_rate = (TextView) itemView.findViewById(R.id.star_rate);
            menu_name = (TextView) itemView.findViewById(R.id.menu_name);
            price = (TextView) itemView.findViewById(R.id.price);

            itemView.setOnClickListener(this);
        }

        void onBind(menu_item item) {

            MyRecyclerAdapter adapter = new MyRecyclerAdapter();

            menu_image.setImageResource(item.getResourceId());
            bistro_name.setText(item.getBistro_name());
            star_rate.setText(item.getStar_rate());
            menu_name.setText(item.getMenu_name());
            price.setText(item.getPrice());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(ViewHolder.this, view, position);

                }
            }
        }
    }
}
