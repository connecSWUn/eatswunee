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

    @GET("/menu/{menuId}")
    Call<Result> getData(@Path("menuId") long menuId);

    @GET("/{path}/{id}")
    Call<Result> getData(@Path("path") String path, @Path("id") long id);

    /* 메뉴 검색 API */
    @GET("/gusia/search/{restaurantId}/{keyword}")
    Call<Result> getData(@Path("restaurantId") long restaurantId, @Path("keyword") String keyword);

    @FormUrlEncoded
    @POST("/recruit/save")
    Call<Result> postData(@FieldMap HashMap<String, Object> param);
}
