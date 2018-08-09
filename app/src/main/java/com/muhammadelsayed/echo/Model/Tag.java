package com.muhammadelsayed.echo.Model;

import java.util.Arrays;

public class Tag {

    private String id;
    private String type;
    private String webTitle;
    private String webUrl;
    private String apiUrl;
    private String[] references;
    private String bio;
    private String bylineImageUrl;
    private String bylineLargeImageUrl;
    private String firstName;
    private String lastName;
    private String twitterHandle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String[] getReferences() {
        return references;
    }

    public void setReferences(String[] references) {
        this.references = references;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBylineImageUrl() {
        return bylineImageUrl;
    }

    public void setBylineImageUrl(String bylineImageUrl) {
        this.bylineImageUrl = bylineImageUrl;
    }

    public String getBylineLargeImageUrl() {
        return bylineLargeImageUrl;
    }

    public void setBylineLargeImageUrl(String bylineLargeImageUrl) {
        this.bylineLargeImageUrl = bylineLargeImageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", references=" + Arrays.toString(references) +
                ", bio='" + bio + '\'' +
                ", bylineImageUrl='" + bylineImageUrl + '\'' +
                ", bylineLargeImageUrl='" + bylineLargeImageUrl + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", twitterHandle='" + twitterHandle + '\'' +
                '}';
    }
}
