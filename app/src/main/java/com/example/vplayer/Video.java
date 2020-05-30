package com.example.vplayer;

public class Video {
    private int id;
    private String name;
    private String like;
    private String dislike;
    private String link;
    private String bookmark;

    public Video(){}

    public Video(int id, String name, String like, String dislike, String link, String bookmark) {
        this.id = id;
        this.name = name;
        this.like = like;
        this.dislike = dislike;
        this.link = link;
        this.bookmark = bookmark;
    }

    public Video(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }
}
