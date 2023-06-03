package com.example.eatswunee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatswunee.server.AccountLoginDto;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;
import com.example.eatswunee.server.Utils;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    String user_id, user_pw;
    TextInputEditText id_input, password_input;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utils.init(getApplicationContext());

        id_input = findViewById(R.id.idInput);
        password_input = findViewById(R.id.passwordInput);
        login_btn = findViewById(R.id.login_btn);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_id = id_input.getText().toString();
                user_pw = password_input.getText().toString();
                AccountLoginDto account = new AccountLoginDto(user_id, user_pw);

                serviceApi.postData(account).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(!response.isSuccessful()) {
                            Log.e("연결이 비정상적 : ", "error code : " + response.code());
                        } else {
                            Utils.setAccessToken(response.body().getData().getAccessToken());
                            Utils.setRefreshtoken(response.body().getData().getRefreshToken());
                            Log.e("Login", "at : " + Utils.getAccessToken("1234"));
                            Log.e("Login", "rt : " + Utils.getRefreshToken("none"));
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}