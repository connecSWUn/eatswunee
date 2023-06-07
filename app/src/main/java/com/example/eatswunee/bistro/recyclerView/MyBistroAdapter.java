package com.example.eatswunee.bistro.recyclerView;

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
import com.example.eatswunee.bistro.menu_infoActivity;
import com.example.eatswunee.community.ServiceItemClickListener;
import com.example.eatswunee.mypage.MyOwnReviewAdapter;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Post;
import com.example.eatswunee.server.menus;
import com.example.eatswunee.server.orders;
import com.example.eatswunee.server.restaurants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyBistroAdapter extends RecyclerView.Adapter<MyBistroAdapter.ViewHolder> {

    private List<menus> menusList;

    public MyBistroAdapter(List<menus> menusList) { this.menusList = menusList; }

    @NonNull
    @Override
    public MyBistroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bistro_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBistroAdapter.ViewHolder holder, int position) {
        menus item = menusList.get(position);
        holder.setItem(item);

        holder.serviceItemClickListener = new ServiceItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                long menuId = menusList.get(position).getMenuId();
                String menuImage = menusList.get(position).getMenuImg();
                Intent intent = new Intent(v.getContext(), menu_infoActivity.class);
                intent.putExtra("menuId", menuId);
                intent.putExtra("menuImage", menuImage);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return menusList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView restaurant_name, menu_name, menu_price, menu_avg_rating;
        ImageView menu_image;
        ServiceItemClickListener serviceItemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menu_image = (ImageView) itemView.findViewById(R.id.menu_image);
            restaurant_name = (TextView) itemView.findViewById(R.id.bistro_name);
            menu_avg_rating = (TextView) itemView.findViewById(R.id.star_rate);
            menu_name = (TextView) itemView.findViewById(R.id.menu_name);
            menu_price = (TextView) itemView.findViewById(R.id.price);

            itemView.setOnClickListener(this);
        }

        void setItem(menus menus) {
            restaurant_name.setText(menus.getRestaurantName());
            menu_avg_rating.setText(String.valueOf(menus.getMenuRating()));
            menu_name.setText(menus.getMenuName());
            menu_price.setText(menus.getMenuPrice());
            new ImageLoadTask(menus.getMenuImg(), menu_image).execute();
        }

        @Override
        public void onClick(View v) {
            this.serviceItemClickListener.onItemClickListener(v, getLayoutPosition());
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
