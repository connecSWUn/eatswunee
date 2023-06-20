package com.example.eatswunee.server.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    // DB 관련 상수 선언
    public static final String DATABASE_NAME = "shopping_bag.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "shopping_bag";

    public static final String COLUMN_ID = "menu_id";
    public static final String COLUMN_NAME = "menu_name";
    public static final String COLUMN_IMAGE_URL = "menu_image";
    public static final String COLUMN_PRICE = "menu_price";
    public static final String COLUMN_RES_NAME = "restaurant_name";
    public static final String COLUMN_CNT = "menu_cnt";

    // 부가적인 객체들
    private Context context;


    /** DB 생성 **/
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // 생성된 DB가 없을 경우에 한 번만 호출됨
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createSql = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " LONG PRIMARY KEY, " + COLUMN_NAME + " TEXT, "
                + COLUMN_IMAGE_URL + " TEXT, " + COLUMN_PRICE + " INTEGER, "
                + COLUMN_RES_NAME + " TEXT, " + COLUMN_CNT + " INTEGER)";
        sqLiteDatabase.execSQL(createSql);
        Log.d("sqlite", "DB is opened");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    // 데이터 전체 검색
    public Cursor readAllData() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(sql, null);
        }

        return cursor;
    }

    /**
     * 장바구니 등록
     * @param menu_id 이름
     * @param menu_name 메뉴 이름
     * @param menu_image 메뉴 사진
     * @param menu_price 메뉴 가격
     * @param restaurant_name 식당 이름
     * @param menu_cnt 메뉴 수량
     */
    public void addBag(long menu_id, String menu_name, String menu_image, int menu_price, String restaurant_name, int menu_cnt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, menu_id);
        cv.put(COLUMN_NAME, menu_name);
        cv.put(COLUMN_IMAGE_URL, menu_image);
        cv.put(COLUMN_PRICE, menu_price);
        cv.put(COLUMN_RES_NAME, restaurant_name);
        cv.put(COLUMN_CNT, menu_cnt);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Log.d("sqlite", "Insert Failed");
        } else {
            Log.d("sqlite", "Insert Successfully");
        }
    }


    /**
     * 연락처 수정
     * @param menu_name 메뉴 이름
     * @param menu_cnt 메뉴 수량
     */
    public void updateData(String menu_name, int menu_cnt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // 수정할 값
        cv.put(COLUMN_CNT, menu_cnt);

        long result = db.update(TABLE_NAME, cv, "menu_name=?", new String[]{menu_name});

        if(result == -1) {
            Log.d("sqlite", "Failed");
        } else {
            Log.d("sqlite", "update successfully");
        }
    }


    /**
     * 연락처 삭제
     * @param menu_name 메뉴 이름
     */
    void deleteData(String menu_name) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "menu_name=?", new String[]{menu_name});

        if (result == -1) {
            Log.d("sqlite", "Failed");
        } else {
            Log.d("sqlite", "delete successfully");
        }
    }

    /**
     * 데이터 전체 삭제
     */
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
