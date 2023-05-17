package com.example.eatswunee.community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.server.Post;

import java.util.ArrayList;
import java.util.List;

public class MyCommunityAdapter extends RecyclerView.Adapter<MyCommunityAdapter.ViewHolder> {

    private List<Post> items;

    public MyCommunityAdapter(List<Post> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyCommunityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사진 데이터 추가 시 if문 구분 필요
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommunityAdapter.ViewHolder holder, int position) {
        Post item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, place, app_time, post_date, state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.com_title);
            place = (TextView) itemView.findViewById(R.id.com_place);
            app_time = (TextView) itemView.findViewById(R.id.com_time);
            post_date = (TextView) itemView.findViewById(R.id.com_date);
            state = (TextView) itemView.findViewById(R.id.com_state);
        }

        void setItem(Post item) {
            title.setText(item.getTitle());
            place.setText(item.getSpot());
            app_time.setText(item.getStartTime() + "-" + item.getEndTime());
            post_date.setText(item.getCreatedAt());

            if(item.getStatus() == "FINDING") {
                state.setText("찾는 중...");
                state.setBackgroundResource(R.drawable.community_state_finding);
            } else if (item.getStatus() == "CONNECTING") {
                state.setText("연락 중...");
                state.setBackgroundResource(R.drawable.community_state_talking);
            } else if (item.getStatus() == "FOUND") {
                state.setText("구했어요!");
                state.setBackgroundResource(R.drawable.community_state_done);
            }
        }
    }
}
