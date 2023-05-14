package com.example.eatswunee.server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    /* Get 방식 - @GET(URI) */
    @GET("/recruit/post/all?cursorId=10003034")
    Call<Result> getData(@Query("cursorId") String cursorId, @Query("category") String category);
}
