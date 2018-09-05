package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 02-Jul-18.
 */

public class Review {
    private String content;
    private String author;
    private String authorImage;
    private String rottenTomatoesType;
    private String reviewDate;

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public String getRottenTomatoesType() {
        return rottenTomatoesType;
    }

    public void setRottenTomatoesType(String rottenTomatoesType) {
        this.rottenTomatoesType = rottenTomatoesType;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
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
