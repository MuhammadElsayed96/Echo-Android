package com.muhammadelsayed.echo.model;

import java.net.URL;

public class Source {
    private String id;
    private String name;
    private String description;
    private URL url;
    private String category;
    private String language;
    private String country;
    private int mImageResourceID;
    private boolean mToggleStatus;

    public Source() {
    }

    public Source(String id, int mImageResourceID, boolean mToggleStatus) {
        this.id = id;
        this.mImageResourceID = mImageResourceID;
        this.mToggleStatus = mToggleStatus;
    }

    public Source(String id, String name, String description, URL url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public void setImageResourceID(int mImageResourceID) {
        this.mImageResourceID = mImageResourceID;
    }

    public boolean isToggleStatus() {
        return mToggleStatus;
    }

    public void setToggleStatus(boolean mToggleStatus) {
        this.mToggleStatus = mToggleStatus;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url=" + url +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mToggleStatus=" + mToggleStatus +
                '}';
    }
}
