package com.example.vplayer.disLikeDataPack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.vplayer.LikeDislikeVideo;
import com.example.vplayer.VideoData.Params;
import com.example.vplayer.likeDataPack.ParamLike;
import com.like.LikeButton;

public class MyDbDisLike extends SQLiteOpenHelper {
    public MyDbDisLike(@Nullable Context context) {
        super(context, ParamDisLike.TABLE_NAME, null, ParamDisLike.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + ParamDisLike.TABLE_NAME + "("
                + ParamDisLike.KEY_ID + " INTEGER PRIMARY KEY," + ParamDisLike.KEY_NAME
                + " TEXT " + ")";
        Log.d("dbPrabhat", "Query being run is : "+ create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public boolean isExist(String name){
        boolean x = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + ParamDisLike.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            if(cursor.getString(cursor.getColumnIndex("videoname")).equals(name)){
                x = true;
                break;
            }
        }
        return x;
    }

    public void addDisLike(LikeDislikeVideo contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ParamDisLike.KEY_NAME, contact.getVideoName());


        db.insert(ParamDisLike.TABLE_NAME, null, values);
        Log.d("dbPrabhat", "Successfully inserted");
        db.close();
    }
    public void deleteDisLike(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ParamDisLike.TABLE_NAME,ParamDisLike.KEY_NAME +"=?", new String[]{String.valueOf(name)});
        db.close();
    }
    public int getCount(){
        String query = "SELECT * FROM "+ ParamDisLike.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c.getCount();
    }
}
