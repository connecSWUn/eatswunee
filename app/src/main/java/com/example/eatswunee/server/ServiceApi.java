package com.example.eatswunee.server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    /* Get 방식 - @GET(URI) */
    @GET("/recruit/list/{category}")
    Call<CommunityResponse> getData(@Path("category") String category);
}
