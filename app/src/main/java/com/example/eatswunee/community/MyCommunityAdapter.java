package com.example.eatswunee.community;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.server.Post;

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

        holder.serviceItemClickListener = new ServiceItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Long recruitId = items.get(position).getRecruitId();
                Intent intent = new Intent(v.getContext(), friend_viewActivity.class);
                intent.putExtra("recruitId", recruitId);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title, place, app_time, post_date, status;
        private ImageView statusImg;
        ServiceItemClickListener serviceItemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusImg = (ImageView) itemView.findViewById(R.id.community_image);
            title = (TextView) itemView.findViewById(R.id.shopbag_menu_name);
            place = (TextView) itemView.findViewById(R.id.com_place);
            app_time = (TextView) itemView.findViewById(R.id.shopbag_price);
            post_date = (TextView) itemView.findViewById(R.id.my_photoR_date);
            status = (TextView) itemView.findViewById(R.id.com_status);

            itemView.setOnClickListener(this);
        }

        void setItem(Post item) {
            title.setText(item.getTitle());
            place.setText(item.getSpot());
            app_time.setText(item.getStartTime() + " - " + item.getEndTime());
            post_date.setText(item.getCreatedAt());

            if(item.getRecruitStatus() == "ONGOING") {
                status.setText("찾는 중...");
                statusImg.setImageResource(R.drawable.baseline_search_24);
                status.setBackgroundResource(R.drawable.community_state_finding);
            } else if (item.getRecruitStatus() == "CONNECTING") {
                status.setText("연락 중...");
                statusImg.setImageResource(R.drawable.baseline_question_answer_24);
                status.setBackgroundResource(R.drawable.community_state_talking);
            } else if (item.getRecruitStatus() == "COMPLETED") {
                status.setText("구했어요!");
                statusImg.setImageResource(R.drawable.baseline_handshake_24);
                status.setBackgroundResource(R.drawable.community_state_done);
            }
        }

        @Override
        public void onClick(View v) {
            this.serviceItemClickListener.onItemClickListener(v, getLayoutPosition());
        }
    }
}
