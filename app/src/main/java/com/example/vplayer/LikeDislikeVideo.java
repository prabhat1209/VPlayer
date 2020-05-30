package com.example.vplayer;

public class LikeDislikeVideo {
    public String videoName;

    public LikeDislikeVideo(String videoName) {
        this.videoName = videoName;
    }

    public LikeDislikeVideo() { }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
