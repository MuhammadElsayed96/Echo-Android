package com.muhammadelsayed.echo.model;

public class Section {
    private int resId;
    private String title;

    public Section() {
    }

    public Section(int resId, String title) {
        this.resId = resId;
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Section{" +
                "resId=" + resId +
                ", title='" + title + '\'' +
                '}';
    }
}
