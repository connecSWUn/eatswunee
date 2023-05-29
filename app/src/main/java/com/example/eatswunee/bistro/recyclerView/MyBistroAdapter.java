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
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Post;
import com.example.eatswunee.server.menus;
import com.example.eatswunee.server.orders;
import com.example.eatswunee.server.restaurants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyBistroAdapter extends RecyclerView.Adapter<MyBistroAdapter.ViewHolder> {

    private List<menus> menusList;
    ImageView menu_image;

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
                Intent intent = new Intent(v.getContext(), menu_infoActivity.class);
                intent.putExtra("menuId", menuId);
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
            new DownloadFilesTask().execute(menus.getMenuImg());
        }

        @Override
        public void onClick(View v) {
            this.serviceItemClickListener.onItemClickListener(v, getLayoutPosition());
        }
    }

    class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            menu_image.setImageBitmap(result);
        }
    }
}
