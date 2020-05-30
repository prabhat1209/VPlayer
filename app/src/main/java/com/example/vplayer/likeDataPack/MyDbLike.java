package com.example.vplayer.likeDataPack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.vplayer.LikeDislikeVideo;

public class MyDbLike extends SQLiteOpenHelper {
    public MyDbLike(@Nullable Context context) {
        super(context, ParamLike.TABLE_NAME, null, ParamLike.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + ParamLike.TABLE_NAME + "("
                + ParamLike.KEY_ID + " INTEGER PRIMARY KEY," + ParamLike.KEY_NAME
                + " TEXT " + ")";
        Log.d("dbPrabhat", "Query being run is : "+ create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public boolean isExist(String name){
        boolean x = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + ParamLike.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            LikeDislikeVideo contact = new LikeDislikeVideo();
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            if(cursor.getString(cursor.getColumnIndex("videoname")).equals(name)){
                x = true;
                break;
            }
        }
        return x;
    }
    public void addLike(LikeDislikeVideo contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ParamLike.KEY_NAME, contact.getVideoName());


        db.insert(ParamLike.TABLE_NAME, null, values);
        Log.d("dbPrabhat", "Successfully inserted");
        db.close();
    }
    public void deleteLike(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ParamLike.TABLE_NAME,ParamLike.KEY_NAME +"=?", new String[]{String.valueOf(name)});
        db.close();
    }

    public int getCount(){
        String query = "SELECT * FROM "+ ParamLike.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c.getCount();
    }
}
