package com.example.eatswunee.server;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {
    /* Get 방식 - @GET(URI) */
    @GET("/recruit/list/{category}")
    Call<Result> getData(@Path("category") String category);

    @GET("/recruit/{postId}")
    Call<Result> getData(@Path("postId") long postId);

    @FormUrlEncoded
    @POST("/recruit/save")
    Call<Result> postData(@FieldMap HashMap<String, Object> param);
}
