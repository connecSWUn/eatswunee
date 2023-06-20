package com.example.eatswunee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatswunee.server.AccountLoginDto;
import com.example.eatswunee.server.AccountRegisterDto;
import com.example.eatswunee.server.Data;
import com.example.eatswunee.server.Result;
import com.example.eatswunee.server.RetrofitClient;
import com.example.eatswunee.server.ServiceApi;
import com.example.eatswunee.server.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText idInput, passwordInput, confirmPassword, nickname;
    private Button is_id_duplicated, is_nickname_duplicated;
    private Button registerBtn, loginBtn;

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getServiceApi();

        idInput = findViewById(R.id.idInput);
        passwordInput = findViewById(R.id.join_pw);
        nickname = findViewById(R.id.nickInput);
        confirmPassword = findViewById(R.id.join_pwck);

        is_id_duplicated = findViewById(R.id.is_id);
        is_nickname_duplicated = findViewById(R.id.is_nick);

        registerBtn = findViewById(R.id.join_button);
        loginBtn = findViewById(R.id.register_login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(is_id_duplicated.getText().toString() == "중복확인") {
                    Toast.makeText(RegisterActivity.this, "아이디 중복확인을 해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (is_nickname_duplicated.getText().toString() == "중복확인") {
                    Toast.makeText(RegisterActivity.this, "닉네임 중복확인을 해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(passwordInput.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(confirmPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "비밀번호 확인을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if(passwordInput.getText().toString().equals(confirmPassword.getText().toString())) {
                        String user_id = idInput.getText().toString();
                        String user_pw = passwordInput.getText().toString();
                        String nick = nickname.getText().toString();
                        AccountRegisterDto account = new AccountRegisterDto(user_id, nick, user_pw);

                        serviceApi.postRegister(account).enqueue(new Callback<Result>() {
                            @Override
                            public void onResponse(Call<Result> call, Response<Result> response) {
                                if(!response.isSuccessful()) {
                                    Log.e("연결이 비정상적 : ", "error code : " + response.code());
                                } else {
                                    Toast.makeText(RegisterActivity.this, "가입되었습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<Result> call, Throwable t) {

                            }
                        });
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "비밀번호가 동일하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        
        // 아이디 중복 체크
        is_id_duplicated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(idInput.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String loginId = idInput.getText().toString();
                    serviceApi.isIdDuplicated(loginId).enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            Result result = response.body();
                            Data data = result.getData();
                            
                            if(data.isIs_duplicated()) {
                                Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                is_id_duplicated.setBackgroundResource(R.drawable.order_list_btn_unclickable);
                                is_id_duplicated.setTextColor(Color.WHITE);
                                is_id_duplicated.setText("확인 완료");
                            }
                            return;
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                        }
                    });
                }
            }
        });

        is_nickname_duplicated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(nickname.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "닉네임을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String nick = nickname.getText().toString();

                    serviceApi.isNicknameDuplicated(nick).enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            Result result = response.body();
                            Data data = result.getData();

                            if(data.isIs_duplicated()) {
                                Toast.makeText(RegisterActivity.this, "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                is_nickname_duplicated.setBackgroundResource(R.drawable.order_list_btn_unclickable);
                                is_nickname_duplicated.setTextColor(Color.WHITE);
                                is_nickname_duplicated.setText("확인 완료");
                            }
                            return;
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}