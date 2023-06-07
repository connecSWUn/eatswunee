package com.example.eatswunee.community;

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

public class MyArticlesAdapter extends RecyclerView.Adapter<MyArticlesAdapter.ViewHolder> {

    private List<Post> postList;

    public MyArticlesAdapter(List<Post> postList) { this.postList = postList; }

    @NonNull
    @Override
    public MyArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArticlesAdapter.ViewHolder holder, int position) {
        Post item = postList.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return postList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, place, app_time, post_date, status;
        private ImageView statusImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusImg = (ImageView) itemView.findViewById(R.id.community_image);
            title = (TextView) itemView.findViewById(R.id.shopbag_menu_name);
            place = (TextView) itemView.findViewById(R.id.com_place);
            app_time = (TextView) itemView.findViewById(R.id.shopbag_price);
            post_date = (TextView) itemView.findViewById(R.id.my_photoR_date);
            status = (TextView) itemView.findViewById(R.id.com_status);

            // itemView.setOnClickListener(this);
        }

        void setItem(Post item) {
            title.setText(item.getPost_title());
            place.setText(item.getPost_spot());
            app_time.setText(item.getPost_start_time() + "-" + item.getPost_end_time());
            post_date.setText(item.getPost_created_at());

            if(item.getPost_recruit_status() == "ONGOING") {
                status.setText("찾는 중...");
                statusImg.setImageResource(R.drawable.baseline_search_24);
                status.setBackgroundResource(R.drawable.community_state_finding);
            } else if (item.getPost_recruit_status() == "CONNECTING") {
                status.setText("연락 중...");
                statusImg.setImageResource(R.drawable.baseline_question_answer_24);
                status.setBackgroundResource(R.drawable.community_state_talking);
            } else if (item.getPost_recruit_status() == "COMPLETED") {
                status.setText("구했어요!");
                statusImg.setImageResource(R.drawable.baseline_handshake_24);
                status.setBackgroundResource(R.drawable.community_state_done);
            }
        }
    }
}
