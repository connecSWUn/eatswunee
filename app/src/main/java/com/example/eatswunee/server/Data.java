package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Data {

    /* 로그인 */
    @SerializedName("grantType")
    private String grantType;
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("refreshToken")
    private String refreshToken;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("cursorId")
    @Expose
    private String cursorId;
    @SerializedName("post")
    @Expose
    private List<Post> postList = null;


    @SerializedName("post_id")
    private long post_id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("title")
    private String title;
    @SerializedName("spot")
    private String spot;
    @SerializedName("start_time")
    private String start_time;
    @SerializedName("end_time")
    private String end_time;
    @SerializedName("recruit_status")
    private String recruit_status;
    @SerializedName("writer")
    private writer writers = null;
    @SerializedName("content")
    private String content;
    @SerializedName("user_is_writer")
    private boolean user_is_writer;

    /* 식당별 메뉴 리스트 조회 API */
    @SerializedName("homeOrders")
    private List<homeOrders> homeOrdersList = null;
    @SerializedName("menus")
    private List<menus> menusList = null;
    @SerializedName("restaurants")
    private List<restaurants> restaurantsList = null;

    /* 메뉴 내용 조회 API */
    @SerializedName("menuId")
    @Expose
    private long menuId;
    @SerializedName("RestaurantName")
    @Expose
    private String RestaurantName;
    @SerializedName("menuName")
    @Expose
    private String menuName;
    @SerializedName("menuPrice")
    @Expose
    private int menuPrice;
    @SerializedName("menuRating")
    @Expose
    private float menuRating;
    @SerializedName("menuReviewCnt")
    @Expose
    private float menuReviewCnt;

    /* 메뉴 검색 API */
    @SerializedName("searchedMenus")
    @Expose
    private List<searchedMenus> searchedMenusList;

    /* 메뉴의 리뷰 조회 */
    @SerializedName("reviewCnt")
    @Expose
    private long reviewCnt;
    @SerializedName("menuImg")
    @Expose
    private String menuImg;
    @SerializedName("menuAvgRating")
    @Expose
    private float menuAvgRating;
    @SerializedName("reviewRating")
    private reviewRating reviewRating;
    @SerializedName("reviews")
    private List<reviews> reviewsList;

    /* 주문 내역 조회 */

    @SerializedName("orders")
    private List<orders> ordersList;
    @SerializedName("order_num")
    @Expose
    private String order_num;
    @SerializedName("order_created_at")
    @Expose
    private String order_created_at;
    @SerializedName("order_total_price")
    @Expose
    private String order_total_price;

    public List<orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<orders> ordersList) {
        this.ordersList = ordersList;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_created_at() {
        return order_created_at;
    }

    public void setOrder_created_at(String order_created_at) {
        this.order_created_at = order_created_at;
    }

    public String getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(String order_total_price) {
        this.order_total_price = order_total_price;
    }


    /* 마이페이지 화면 조회 */

    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("user_profile_url")
    @Expose
    private String user_profile_url;
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("loginId")
    @Expose
    private String loginId;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_profile_url() {
        return user_profile_url;
    }

    public void setUser_profile_url(String user_profile_url) {
        this.user_profile_url = user_profile_url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /* 작성 게시물 조회 */
    @SerializedName("userId")
    private long userId;
    @SerializedName("userName")
    private String userName;
    @SerializedName("postTotalCnt")
    private int postTotalCnt;
    @SerializedName("posts")
    private List<posts> postsList;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPostTotalCnt() {
        return postTotalCnt;
    }

    public void setPostTotalCnt(int postTotalCnt) {
        this.postTotalCnt = postTotalCnt;
    }

    public List<posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<posts> postsList) {
        this.postsList = postsList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCursorId() {
        return cursorId;
    }

    public void setCursorId(String cursorId) {
        this.cursorId = cursorId;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    /* 채팅방 존재 여부 확인 */
    @SerializedName("exist_chatroom")
    @Expose
    private boolean exist_chatroom;

    public boolean isExist_chatroom() {
        return exist_chatroom;
    }

    public void setExist_chatroom(boolean exist_chatroom) {
        this.exist_chatroom = exist_chatroom;
    }

    /* 채팅방 입장 */
    @SerializedName("recruitStatus")
    @Expose
    private String recruitStatus;
    @SerializedName("recruit_title")
    @Expose
    private String recruit_title;
    @SerializedName("recruit_spot")
    @Expose
    private String recruit_spot;
    @SerializedName("recruit_start_time")
    @Expose
    private String recruit_start_time;
    @SerializedName("recruit_end_time")
    @Expose
    private String recruit_end_time;
    @SerializedName("recruit_created_at")
    @Expose
    private String recruit_created_at;
    @SerializedName("sender_name")
    @Expose
    private String sender_name;
    @SerializedName("commands")
    private List<messages> messagesList;

    public String getRecruitStatus() {
        return recruitStatus;
    }

    public void setRecruitStatus(String recruitStatus) {
        this.recruitStatus = recruitStatus;
    }

    public String getRecruit_title() {
        return recruit_title;
    }

    public void setRecruit_title(String recruit_title) {
        this.recruit_title = recruit_title;
    }

    public String getRecruit_spot() {
        return recruit_spot;
    }

    public void setRecruit_spot(String recruit_spot) {
        this.recruit_spot = recruit_spot;
    }

    public String getRecruit_start_time() {
        return recruit_start_time;
    }

    public void setRecruit_start_time(String recruit_start_time) {
        this.recruit_start_time = recruit_start_time;
    }

    public String getRecruit_end_time() {
        return recruit_end_time;
    }

    public void setRecruit_end_time(String recruit_end_time) {
        this.recruit_end_time = recruit_end_time;
    }

    public String getRecruit_created_at() {
        return recruit_created_at;
    }

    public void setRecruit_created_at(String recruit_created_at) {
        this.recruit_created_at = recruit_created_at;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public List<messages> getCommandsList() {
        return messagesList;
    }

    public void setCommandsList(List<messages> messagesList) {
        this.messagesList = messagesList;
    }

    /* 채팅방 만들기 */
    @SerializedName("chat_room_id")
    @Expose
    private long chat_room_id;

    public long getChat_room_id() {
        return chat_room_id;
    }

    public void setChat_room_id(long chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    /* 채팅방 리스트 */
    @SerializedName("chatRooms")
    private List<chatRooms> chatRoomsList;

    public List<chatRooms> getChatRoomsList() {
        return chatRoomsList;
    }

    public void setChatRoomsList(List<chatRooms> chatRoomsList) {
        this.chatRoomsList = chatRoomsList;
    }



    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRecruit_status() {
        return recruit_status;
    }

    public void setRecruit_status(String recruit_status) {
        this.recruit_status = recruit_status;
    }

    public writer getWriters() {
        return writers;
    }

    public void setWriters(writer writers) {
        this.writers = writers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isUser_is_writer() {
        return user_is_writer;
    }

    public void setUser_is_writer(boolean user_is_writer) {
        this.user_is_writer = user_is_writer;
    }

    /* 식당별 메뉴 조회 */

    public List<homeOrders> getHomeOrdersList() {
        return homeOrdersList;
    }

    public void setHomeOrdersList(List<homeOrders> homeOrdersList) {
        this.homeOrdersList = homeOrdersList;
    }

    public List<menus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<menus> menusList) {
        this.menusList = menusList;
    }

    public List<restaurants> getRestaurantsList() {
        return restaurantsList;
    }

    public void setRestaurantsList(List<restaurants> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    /* 메뉴 내용 조회 */
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public float getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(float menuRating) {
        this.menuRating = menuRating;
    }

    public float getMenuReviewCnt() {
        return menuReviewCnt;
    }

    public void setMenuReviewCnt(float menuReviewCnt) {
        this.menuReviewCnt = menuReviewCnt;
    }


    /* 메뉴 검색 API */
    public List<searchedMenus> getSearchedMenusList() {
        return searchedMenusList;
    }

    public void setSearchedMenusList(List<searchedMenus> searchedMenusList) {
        this.searchedMenusList = searchedMenusList;
    }

    /* 메뉴 리뷰 조회 */

    public long getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(long reviewCnt) {
        this.reviewCnt = reviewCnt;
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
    }

    public float getMenuAvgRating() {
        return menuAvgRating;
    }

    public void setMenuAvgRating(float menuAvgRating) {
        this.menuAvgRating = menuAvgRating;
    }

    public reviewRating getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(reviewRating reviewRating) {
        this.reviewRating = reviewRating;
    }

    public List<reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }
}
