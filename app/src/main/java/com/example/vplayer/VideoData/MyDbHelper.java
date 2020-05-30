package com.example.vplayer.VideoData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.vplayer.Video;
import com.example.vplayer.bookmarked.BookMarkedVideo;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("ok");
        String create = "CREATE TABLE " + Params.TABLE_NAME + " ("
                + Params.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Params.KEY_NAME
                + " TEXT, " + Params.KEY_LINK + " TEXT, " + Params.KEY_LIKE + " TEXT, " + Params.KEY_DISLIKE + " TEXT, " + Params.KEY_BOOKMARK + " TEXT)";
        Log.d("dbStart", "Query being run is : "+ create);
        db.execSQL(create);

    }

    public void add(Video video){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, video.getName());
        values.put(Params.KEY_LINK, video.getLink());
        values.put(Params.KEY_LIKE, video.getLike());
        values.put(Params.KEY_DISLIKE, video.getDislike());
        values.put(Params.KEY_BOOKMARK, video.getBookmark());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("dbPrabhat", "Successfully inserted");
        db.close();
    }

    public ArrayList<BookMarkedVideo> getAll(){
        ArrayList<BookMarkedVideo> notList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                BookMarkedVideo video = new BookMarkedVideo();
                video.setBook_id(Integer.parseInt(cursor.getString(0)));
                video.setBook_name(cursor.getString(1));
                video.setBook_link(cursor.getString(2));
                notList.add(video);
            }while(cursor.moveToNext());
        }
        return notList;
    }

    public boolean isExist(String name){
        boolean x = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex("videoname")).equals(name)){
                x = true;
                break;}
        }
        return x;
    }

    public void deleteBooked(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_NAME +"=?", new String[]{String.valueOf(name)});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public int getCount(){
        String query = "SELECT * FROM "+ Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c.getCount();
    }
}
