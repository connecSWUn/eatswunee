package com.example.eatswunee.server;

import com.example.eatswunee.community.article;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
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

    /* 리뷰 불러오기 */
    @GET("{page}/{object}/{userId}")
    Call<Result> getData(@Path("page") String page, @Path("object") String object, @Path("userId") long userId);

    @GET("/{path}/{id}")
    Call<Result> getData(@Path("path") String path, @Path("id") long id);

    /* 메뉴 검색 API */
    @GET("/gusia/search/{restaurantId}/{keyword}")
    Call<Result> getData(@Path("restaurantId") long restaurantId, @Path("keyword") String keyword);

    /* 마이페이지 화면 조회 */
    @GET("/mypage")
    Call<Result> getProfile();

    /* 작성 글 목록 조회 */
    @GET("/recruit/writer/{recruitStatus}")
    Call<Result> getArticles(@Path("recruitStatus") String recruitStatus);

    /* 마이페이지 주문 목록 조회 */
    @GET("/mypage/order")
    Call<Result> getOrderList();

    /* 마이페이지 리뷰 목록 조회 */
    @GET("/mypage/reviews")
    Call<Result> getReviews();

    /* 로그인 */
    @POST("/login/user")
    Call<Result> postData(@Body AccountLoginDto accountLoginDto);

    @POST("/recruit/save")
    Call<Result> postArticle(@Body article article);
}
