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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.ViewHolder> {

    private List<reviews> reviewsList;

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
        ImageView profile;
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
            new ImageLoadTask(reviews.getWriter().getProfileUrl(), profile).execute();
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
