package com.example.eatswunee.server.sqlite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.bistro.menu_infoActivity;
import com.example.eatswunee.bistro.recyclerView.MyBistroAdapter;
import com.example.eatswunee.mypage.MyListAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;

public class shopbagAdapter extends RecyclerView.Adapter<shopbagAdapter.ViewHolder> {

    Context context;
    ArrayList<shop_bag> bags = new ArrayList<>();
    ImageView menu_image;

    public shopbagAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public shopbagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_shopbag, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull shopbagAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        shop_bag bag = bags.get(position);

        holder.menu_name.setText(bag.getMenu_name());
        holder.menu_price.setText(String.valueOf(bag.getMenu_price()));
        holder.menu_cnt.setText(String.valueOf(bag.getMenu_cnt()));
        holder.total_price.setText(String.valueOf(bag.getMenu_price() * bag.getMenu_cnt()));

        new DownloadFilesTask().execute(bag.getMenu_image());

        holder.cnt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cnt = Integer.parseInt((String) holder.menu_cnt.getText());
                int price = Integer.parseInt((String) holder.menu_price.getText());
                holder.menu_cnt.setText(String.valueOf(cnt + 1));
                holder.total_price.setText(String.valueOf(price * (cnt + 1)));

                int plus_cnt = Integer.parseInt((String) holder.menu_cnt.getText());
                String menu_name = (String) holder.menu_name.getText();

                DBManager db = new DBManager(view.getContext());
                db.updateData(menu_name, plus_cnt);

                Intent intent = ((Activity)context).getIntent();
                ((Activity)context).finish(); //현재 액티비티 종료 실시
                ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                ((Activity)context).startActivity(intent); //현재 액티비티 재실행 실시
                ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
            }
        });

        holder.cnt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cnt = Integer.parseInt((String) holder.menu_cnt.getText());
                int price = Integer.parseInt((String) holder.menu_price.getText());

                if(cnt == 1) Toast.makeText(view.getContext(), "최소 수량입니다.", Toast.LENGTH_SHORT).show();
                else {
                    holder.menu_cnt.setText(String.valueOf(cnt - 1));
                    holder.total_price.setText(String.valueOf(price * (cnt - 1)));

                    int minus_cnt = Integer.parseInt((String) holder.menu_cnt.getText());
                    String menu_name = (String) holder.menu_name.getText();

                    DBManager db = new DBManager(view.getContext());
                    db.updateData(menu_name, minus_cnt);

                    Intent intent = ((Activity)context).getIntent();
                    ((Activity)context).finish(); //현재 액티비티 종료 실시
                    ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                    ((Activity)context).startActivity(intent); //현재 액티비티 재실행 실시
                    ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menu_name = (String) holder.menu_name.getText();

                DBManager db = new DBManager(view.getContext());
                db.deleteData(menu_name);

                bags.remove(position);
                notifyItemRemoved(position);

                Intent intent = ((Activity)context).getIntent();
                ((Activity)context).finish(); //현재 액티비티 종료 실시
                ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                ((Activity)context).startActivity(intent); //현재 액티비티 재실행 실시
                ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
            }
        });
    }

    @Override
    public int getItemCount() { return bags.size(); }


    /* 아이템 삭제 */
    public void removeItem(int position) {
        bags.remove(position);
    }

    public void addItem(shop_bag item) {
        bags.add(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView menu_price, menu_cnt, menu_name, total_price;
        Button cnt_minus, cnt_plus, delete;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);

            menu_name = itemView.findViewById(R.id.shopbag_menu_name);
            menu_price = itemView.findViewById(R.id.shopbag_price);
            menu_cnt = itemView.findViewById(R.id.shopbag_cnt);
            total_price = itemView.findViewById(R.id.shopbag_total_price);
            menu_image = itemView.findViewById(R.id.shop_bag_menu_image);
            cnt_minus = itemView.findViewById(R.id.shopbag_minus);
            cnt_plus = itemView.findViewById(R.id.shopbag_plus);
            delete = itemView.findViewById(R.id.shopbag_delete);
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
