package com.muhammadelsayed.echo.model;

public class Source {
    private String id;
    private String name;
    private String mSourceTitle;
    private int mImageResourceID;
    private boolean mToggleStatus;


    public Source(String mSourceTitle, int mImageResourceID, boolean mToggleStatus) {
        this.mSourceTitle = mSourceTitle;
        this.mImageResourceID = mImageResourceID;
        this.mToggleStatus = mToggleStatus;
    }


    public Source() {
    }

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
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

    public String getSourceTitle() {
        return mSourceTitle;
    }

    public void setSourceTitle(String mSourceTitle) {
        this.mSourceTitle = mSourceTitle;
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
                ", mSourceTitle='" + mSourceTitle + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mToggleStatus=" + mToggleStatus +
                '}';
    }
}
