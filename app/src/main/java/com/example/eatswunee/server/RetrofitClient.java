package com.example.eatswunee.server;

import android.util.Log;

import java.io.IOException;

import kotlin.jvm.Throws;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static Retrofit retrofit;
    private static ServiceApi serviceApi;
    private static String baseUrl = "http://43.201.201.163:8080";

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("login", "retrofit successfully");

        serviceApi = retrofit.create(ServiceApi.class);
    }

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        String token = Utils.getAccessToken("1234");
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            Log.d("login", token);
            Log.d("login", "access success");

            return chain.proceed(newRequest);
        }
    }).build();

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static ServiceApi getServiceApi() {
        return serviceApi;
    }
}
