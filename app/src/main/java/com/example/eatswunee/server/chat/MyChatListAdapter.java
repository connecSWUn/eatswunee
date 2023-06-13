package com.example.eatswunee.server.chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.recyclerView.MyBistroAdapter;
import com.example.eatswunee.community.MyCommunityAdapter;
import com.example.eatswunee.community.ServiceItemClickListener;
import com.example.eatswunee.community.friend_viewActivity;
import com.example.eatswunee.mypage.MyListAdapter;
import com.example.eatswunee.server.Post;
import com.example.eatswunee.server.chatRooms;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MyChatListAdapter extends RecyclerView.Adapter<MyChatListAdapter.ViewHolder> {

    private List<chatRooms> items;

    public MyChatListAdapter(List<chatRooms> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public MyChatListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 사진 데이터 추가 시 if문 구분 필요
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChatListAdapter.ViewHolder holder, int position) {
        chatRooms item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, nickname, message, date;
        private ImageView profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.chat_list_title);
            nickname = itemView.findViewById(R.id.chat_list_name);
            message = itemView.findViewById(R.id.chat_list_message);
            date = itemView.findViewById(R.id.chat_list_date);
            profile = itemView.findViewById(R.id.chat_list_profile);

        }

        void setItem(chatRooms item) {
            title.setText(item.getRecruitTitle());
            nickname.setText(item.getSenderNickname());
            message.setText(item.getLastChatMessage());
            date.setText(item.getLastChatCreatedAt());

            new ImageLoadTask(item.getSenderProfileImgUrl(), profile).execute();
        }
    }

    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }
}
