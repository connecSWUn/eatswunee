package com.example.eatswunee.mypage;

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

public class MyOwnReviewAdapter extends RecyclerView.Adapter<MyOwnReviewAdapter.ViewHolder> {

    private List<reviews> reviewsList;
    ImageView profile, review_photo;

    public MyOwnReviewAdapter(List<reviews> reviewsList) { this.reviewsList = reviewsList; }

    @NonNull
    @Override
    public MyOwnReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_photo, parent, false);
        return new MyOwnReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnReviewAdapter.ViewHolder holder, int position) {
        reviews item = reviewsList.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return reviewsList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView restaurant_name, menu_name, content, created_at;
        RatingBar star_rate;
        ImageView review_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurant_name = (TextView) itemView.findViewById(R.id.my_review_res_name);
            menu_name = (TextView) itemView.findViewById(R.id.my_review_menu_name);
            content = (TextView) itemView.findViewById(R.id.my_review_context);
            created_at = (TextView) itemView.findViewById(R.id.my_review_date);
            star_rate = (RatingBar) itemView.findViewById(R.id.my_review_rate);
            review_img = (ImageView) itemView.findViewById(R.id.my_review_photo);
        }

        void setItem(reviews reviews) {
            restaurant_name.setText(reviews.getRestaurant_name());
            menu_name.setText(reviews.getMenu_name());
            content.setText(reviews.getReview_content());
            created_at.setText(reviews.getReview_created_at());
            star_rate.setRating((float) reviews.getMenu_review_rating());
            new DownloadFilesTask().execute(reviews.getReview_image_url());
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
            review_photo.setImageBitmap(result);
        }
    }
}
