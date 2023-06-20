package com.example.eatswunee.mypage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.community.friend_viewActivity;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;
import com.example.eatswunee.server.reviews;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOwnReviewAdapter extends RecyclerView.Adapter<MyOwnReviewAdapter.ViewHolder> {

    private List<reviews> reviewsList;
    ImageView review_photo;

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    public MyOwnReviewAdapter(List<reviews> reviewsList) { this.reviewsList = reviewsList; }

    @NonNull
    @Override
    public MyOwnReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_review_photo, parent, false);
        return new MyOwnReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnReviewAdapter.ViewHolder holder, int position) {
        reviews item = reviewsList.get(position);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceApi.reviewDelete(item.getReviewId()).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }
        });

        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return reviewsList.size(); }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView restaurant_name, menu_name, content, created_at;
        RatingBar star_rate;
        ImageView review_img;
        Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurant_name = (TextView) itemView.findViewById(R.id.my_photoR_res_name);
            menu_name = (TextView) itemView.findViewById(R.id.my_photoR_menu_name);
            content = (TextView) itemView.findViewById(R.id.my_photoR_content);
            created_at = (TextView) itemView.findViewById(R.id.my_photoR_date);
            star_rate = (RatingBar) itemView.findViewById(R.id.my_photoR_rate);
            review_img = (ImageView) itemView.findViewById(R.id.my_review_photo);
            deleteBtn = (Button) itemView.findViewById(R.id.my_photoR_deleteBtn);
        }

        void setItem(reviews reviews) {

            restaurant_name.setText("[" + reviews.getRestaurant_name() + "]");
            menu_name.setText(reviews.getMenu_name());
            content.setText(reviews.getReview_content());
            created_at.setText(reviews.getReview_created_at());
            star_rate.setRating((float) reviews.getMenu_review_rating());

            if(reviews.getReview_image_url() == null) { review_img.setVisibility(View.GONE); }
            else { new ImageLoadTask(reviews.getReview_image_url(), review_img).execute(); }
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
