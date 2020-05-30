package com.example.vplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");

        final VideoView video = (VideoView) findViewById(R.id.videoView);
        if(name.equals("Shayad"))
            video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.shayad);
        else
        if(name.equals("Broken Angel"))
            video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.lonely);
        else
        if(name.equals("Hawayein"))
            video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.hawayein);
        else
        if(name.equals("Love me like you do"))
            video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.love_me_like_you_do);
        else
        if(name.equals("Tu Har Lamha"))
            video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.tu_har_lamha);


        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


            public void onCompletion(MediaPlayer mp) {
                Intent in = new Intent (getApplicationContext(),MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

}
