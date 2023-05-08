package com.example.eatswunee.bistro.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;

import java.util.ArrayList;

public class MyCommunityAdapter extends RecyclerView.Adapter<MyCommunityAdapter.ViewHolder> {

    private ArrayList<community_item> communityItems = new ArrayList<>();

    @NonNull
    @Override
    public MyCommunityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사진 데이터 추가 시 if문 구분 필요
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommunityAdapter.ViewHolder holder, int position) {
        holder.onBind(communityItems.get(position));
    }

    @Override
    public int getItemCount() {
        return communityItems.size();
    }

    public void addItem(community_item data) { communityItems.add(data); }

    public community_item getItem(int position) {
        return communityItems.get(position); // 아이템 가져오기
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView place;
        TextView app_time;
        TextView post_date;
        TextView state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.com_title);
            place = (TextView) itemView.findViewById(R.id.com_place);
            app_time = (TextView) itemView.findViewById(R.id.com_time);
            post_date = (TextView) itemView.findViewById(R.id.com_date);
            state = (TextView) itemView.findViewById(R.id.com_state);
        }

        void onBind(community_item item) {
            title.setText(item.getTitle());
            place.setText(item.getPlace());
            app_time.setText(item.getApp_time());
            post_date.setText(item.getPost_date());

            if(item.getState() == "F") {
                state.setText("찾는 중...");
                state.setBackgroundResource(R.drawable.community_state_finding);
            } else if (item.getState() == "T") {
                state.setText("연락 중...");
                state.setBackgroundResource(R.drawable.community_state_talking);
            } else if (item.getState() == "D") {
                state.setText("구했어요!");
                state.setBackgroundResource(R.drawable.community_state_done);
            }
        }
    }
}
