package com.example.vplayer.bookmarked;

public class BookMarkedVideo {
    private int book_id;
    private String book_name;
    private String book_link;

    public BookMarkedVideo(int book_id, String book_name, String book_link) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_link = book_link;
    }

    public BookMarkedVideo(int book_id, String book_name) {
        this.book_id = book_id;
        this.book_name = book_name;
    }
    public BookMarkedVideo() { }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_link() {
        return book_link;
    }

    public void setBook_link(String boo_link) {
        this.book_link = boo_link;
    }
}
