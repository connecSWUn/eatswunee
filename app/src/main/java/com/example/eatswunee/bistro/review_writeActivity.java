package com.example.eatswunee.bistro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatswunee.R;
import com.example.eatswunee.mypage.review_content;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class review_writeActivity extends AppCompatActivity {

    private Button cancel, done;
    private TextView res, menu;
    private EditText review_text;
    private RatingBar ratingBar;
    private ImageView review_image_btn;
    private LinearLayout review_image_layout;
    private ImageView review_image;

    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int REQUESTCODE = 101;

    private File tempFile;
    String mCurrentPhotoPath;
    Uri imageURI;
    Uri photoURI, albumURI;

    Double menu_review_rating;
    long menu_id;

    /* Retrofit 연결 */
    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    /* 앱 시작 시 퍼미션 확인 */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        cancel = findViewById(R.id.review_cancel_btn);
        done = findViewById(R.id.review_done_btn);
        review_image_btn = findViewById(R.id.review_img_btn);
        review_image = findViewById(R.id.review_image);
        review_image_layout = findViewById(R.id.review_image_layout);

        res = findViewById(R.id.review_write_res);
        menu = findViewById(R.id.review_write_menu);
        review_text = findViewById(R.id.review_write_content);
        ratingBar = findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        String restaurant_name = intent.getStringExtra("restaurant_name");
        String menu_name = intent.getStringExtra("menu_name");
        menu_id = intent.getLongExtra("menu_id",0);

        res.setText("[" + restaurant_name + "]");
        menu.setText(menu_name);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                menu_review_rating = Double.valueOf(rating);
            }
        });

        review_image_btn.setOnClickListener(new imageOnClickListener());
        done.setOnClickListener(new doneBtnOnClickListener());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class doneBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String image_url = String.valueOf(tempFile);
            String content = review_text.getText().toString();

            review_content review_content = new review_content(menu_id, menu_review_rating, image_url, content);

            serviceApi.postReview(review_content).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if(response.isSuccessful()) {
                        Result result = response.body();
                        Log.d("review", "POST Success");

                        Toast.makeText(review_writeActivity.this, "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }
            });
        }
    }

    private class imageOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            verifyStoragePermissions(review_writeActivity.this);

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            startActivityForResult(intent, REQUEST_TAKE_ALBUM);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_ALBUM) {
            photoURI = data.getData();
            Cursor cursor = null;

            try {
                /*
                 * Uri Schema를
                 * content:/// 에서 file:/// 로 변경한다.
                 */
                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoURI != null;
                cursor = getContentResolver().query(photoURI, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } finally {
                if(cursor != null) {
                    cursor.close();
                }
            }

            setImage();
        }
    }

    private void setImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        review_image_layout.setVisibility(View.GONE);
        review_image.setVisibility(View.VISIBLE);
        review_image.setImageBitmap(originalBm);
    }
}