package com.example.eatswunee.bistro.recyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.server.reviews;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.ViewHolder> {

    private List<reviews> reviewsList;
    ImageView profile;

    public MyReviewAdapter(List<reviews> reviewsList) { this.reviewsList = reviewsList; }

    @NonNull
    @Override
    public MyReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReviewAdapter.ViewHolder holder, int position) {
        reviews item = reviewsList.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return reviewsList.size(); }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, context, date;
        RatingBar star_rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.review_profile);
            name = (TextView) itemView.findViewById(R.id.shopbag_menu_name);
            context = (TextView) itemView.findViewById(R.id.shopbag_price);
            date = (TextView) itemView.findViewById(R.id.my_photoR_date);
            star_rate = (RatingBar) itemView.findViewById(R.id.my_photoR_rate);
        }

        void setItem(reviews reviews) {
            name.setText(reviews.getWriter().getName());
            context.setText(reviews.getReviewContent());
            date.setText(reviews.getCreatedAt());
            star_rate.setRating(reviews.getMenuRating());
            new DownloadFilesTask().execute(reviews.getWriter().getProfileUrl());
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
            profile.setImageBitmap(result);
        }
    }
}
