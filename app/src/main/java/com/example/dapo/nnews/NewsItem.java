package com.example.dapo.nnews;

public class NewsItem {

    private String newsLink;
    private String imgUrl;
    private String title;

    public NewsItem(String imgUrl, String title, String newsLink) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.newsLink = newsLink;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }
}
