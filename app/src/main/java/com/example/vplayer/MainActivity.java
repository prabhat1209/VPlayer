package com.example.vplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.vplayer.VideoData.MyDbHelper;
import com.example.vplayer.bookmarked.BookmarkedActivity;
import com.example.vplayer.disLikeDataPack.MyDbDisLike;
import com.example.vplayer.likeDataPack.MyDbLike;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VideoAdapter videoAdapter;
    static ArrayList<Video> list;
    ToggleButton bookmark;
    ImageView bookmarkedVideo;
    MyDbHelper myDbHelper;
    MyDbDisLike myDbDisLike;
    MyDbLike myDbLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recViewForVideo);
        MyDbHelper db = new MyDbHelper(getApplicationContext());
        bookmark  = findViewById(R.id.bookmark_on_video);
        bookmarkedVideo = findViewById(R.id.bookmarked_section);
        myDbHelper = new MyDbHelper(MainActivity.this);
        myDbLike = new MyDbLike(MainActivity.this);
        myDbDisLike = new MyDbDisLike(getApplicationContext());

        bookmarkedVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookmarkedActivity.class));
                finish();
            }
        });


        list = new ArrayList<>();
        list.add(new Video("Shayad","shayad"));
        list.add(new Video("Broken Angel","lonely"));
        list.add(new Video("Hawayein","hawayein"));
        list.add(new Video("Love me like you do","love_me_like_you_do"));
        list.add(new Video("Tu Har Lamha","tu_har_lamha"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        videoAdapter = new VideoAdapter(list,this, db, myDbLike, myDbDisLike);
        recyclerView.setAdapter(videoAdapter);

        videoAdapter.setOnItemClickListener(new VideoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

            }

            @Override
            public void onDisLikeClick(int pos, boolean state) {
                MyDbDisLike myDbDisLike = new MyDbDisLike(getApplicationContext());
                if(state){
                        String name = list.get(pos).getName();
                        LikeDislikeVideo likeDislikeVideo = new LikeDislikeVideo(name);
                        myDbDisLike.addDisLike(likeDislikeVideo);
                        System.out.println("Total dislike : "+myDbDisLike.getCount());
                }else{
                    if (myDbDisLike.isExist(list.get(pos).getName())) {
                        myDbDisLike.deleteDisLike(list.get(pos).getName());
                    }
                }
            }

            @Override
            public void onLikeClick(int pos, boolean state) {
                MyDbLike myDbLike = new MyDbLike(getApplicationContext());
                if(state){
                        String name = list.get(pos).getName();
                        LikeDislikeVideo likeDislikeVideo = new LikeDislikeVideo(name);
                        myDbLike.addLike(likeDislikeVideo);
                        System.out.println("Total like : "+myDbLike.getCount());
                }else{
                    if (myDbLike.isExist(list.get(pos).getName())) {
                        myDbLike.deleteLike(list.get(pos).getName());
                    }
                }
            }

            @Override
            public void onBookmarkClick(int pos, boolean state) {
                MyDbHelper myDb = new MyDbHelper(MainActivity.this);
                if (state) {
                    int x = pos;
                    if (!myDb.isExist(list.get(x).getName())) {
                        String link = list.get(x).getLink();
                        String name = list.get(x).getName();
                        Video pro = new Video(name,link);
                        myDb.add(pro);
                    }
                //System.out.println("Count : "+myDb.getCount());
                } else {
                    if (myDb.isExist(list.get(pos).getName())) {
                        myDb.deleteBooked(list.get(pos).getName());
                    }
                }
            }

            @Override
            public void playVideo(String name) {
                Intent intent = new Intent(getApplicationContext(),PlayVideoActivity.class);
                intent.putExtra("Name",name);
                startActivity(intent);
            }
        });
    }
}
