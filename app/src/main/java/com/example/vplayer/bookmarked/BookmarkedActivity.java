package com.example.vplayer.bookmarked;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.vplayer.MainActivity;
import com.example.vplayer.PlayBookmarkedVideoActivity;
import com.example.vplayer.R;
import com.example.vplayer.Video;
import com.example.vplayer.VideoData.MyDbHelper;

import java.util.ArrayList;

public class BookmarkedActivity extends AppCompatActivity {

    ImageView backArrow;
    RecyclerView recyclerViewBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarked);

        backArrow = findViewById(R.id.back_arrow);
        recyclerViewBookmarked = findViewById(R.id.recView_bookmarked);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        MyDbHelper db = new MyDbHelper(this);
        final ArrayList<BookMarkedVideo> list = db.getAll();
        recyclerViewBookmarked.setLayoutManager(new LinearLayoutManager(this));

        BookmarkedAdapter adapter = new BookmarkedAdapter(list,this);
        recyclerViewBookmarked.setAdapter(adapter);

        adapter.setOnItemClickListener(new BookmarkedAdapter.ItemClickListener(){

            @Override
            public void playBookmark(String name) {
                Intent intent = new Intent(getApplicationContext(), PlayBookmarkedVideoActivity.class);
                intent.putExtra("Name",name);
                startActivity(intent);
            }
        });

    }
}
