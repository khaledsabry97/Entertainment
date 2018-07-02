package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 02-Jul-18.
 */

public class Review {
    private String content;
    private String author;

    public Review(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
