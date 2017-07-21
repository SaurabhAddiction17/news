package com.blogspot.addictioncodes.news;

/**
 * Created by DELL on 7/17/2017.
 */

public class NewsFormat {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    public NewsFormat(String a,String t,String d,String u,String ut,String p){
        author=a;
        title=t;
        description=d;
        url=u;
        urlToImage=ut;
        publishedAt=p;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}
