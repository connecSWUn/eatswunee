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
import com.example.eatswunee.server.posts;

import java.util.List;

public class MyArticlesAdapter extends RecyclerView.Adapter<MyArticlesAdapter.ViewHolder> {

    private List<posts> postsList;

    public MyArticlesAdapter(List<posts> postsList) { this.postsList = postsList; }

    @NonNull
    @Override
    public MyArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArticlesAdapter.ViewHolder holder, int position) {
        posts item = postsList.get(position);
        holder.setItem(item);

        holder.serviceItemClickListener = new ServiceItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Long recruitId = postsList.get(position).getPostId();
                Intent intent = new Intent(v.getContext(), friend_viewActivity.class);
                intent.putExtra("recruitId", recruitId);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() { return postsList.size(); }

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

        void setItem(posts item) {
            title.setText(item.getPostTitle());
            place.setText(item.getPostSpot());
            app_time.setText(item.getPostStartTime() + "-" + item.getPostEndTime());
            post_date.setText(item.getPostCreatedAt());

            if(item.getPostRecruitStatus() == "ONGOING") {
                status.setText("찾는 중...");
                statusImg.setImageResource(R.drawable.baseline_search_24);
                status.setBackgroundResource(R.drawable.community_state_finding);
            } else if (item.getPostRecruitStatus() == "CONNECTING") {
                status.setText("연락 중...");
                statusImg.setImageResource(R.drawable.baseline_question_answer_24);
                status.setBackgroundResource(R.drawable.community_state_talking);
            } else if (item.getPostRecruitStatus() == "COMPLETED") {
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
