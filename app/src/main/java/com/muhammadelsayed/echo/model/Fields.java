package com.muhammadelsayed.echo.model;

public class Fields {
    private String thumbnail;
    private String showInRelatedContent;
    private String shortUrl;


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShowInRelatedContent() {
        return showInRelatedContent;
    }

    public void setShowInRelatedContent(String showInRelatedContent) {
        this.showInRelatedContent = showInRelatedContent;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "Fields{" +
                "thumbnail='" + thumbnail + '\'' +
                ", showInRelatedContent='" + showInRelatedContent + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
