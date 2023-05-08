package com.example.eatswunee.bistro.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;

import java.util.ArrayList;

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.ViewHolder> {

    private ArrayList<reviewItem> reviewItems = new ArrayList<>();

    @NonNull
    @Override
    public MyReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사진 데이터 추가 시 if문 구분 필요
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReviewAdapter.ViewHolder holder, int position) {
        holder.onBind(reviewItems.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewItems.size();
    }

    public void addItem(reviewItem data) { reviewItems.add(data); }

    public reviewItem getItem(int position) {
        return reviewItems.get(position); // 아이템 가져오기
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        TextView message;
        TextView date;
        RatingBar star_rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.review_profile);
            name = (TextView) itemView.findViewById(R.id.com_title);
            message = (TextView) itemView.findViewById(R.id.com_time);
            date = (TextView) itemView.findViewById(R.id.com_date);
            star_rate = (RatingBar) itemView.findViewById(R.id.review_rate);
        }

        void onBind(reviewItem item) {
            profile.setImageResource(item.getResourceId());
            name.setText(item.getName());
            message.setText(item.getMessage());
            date.setText(item.getDate());
            star_rate.setTag(item.getStar_rate());
        }
    }
}
