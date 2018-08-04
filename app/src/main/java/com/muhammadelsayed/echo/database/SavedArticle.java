package com.muhammadelsayed.echo.database;

public class SavedArticle {
    public static final String TABLE_NAME = "saved";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ARTICLE_ID = "article_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_SECTION_ID = "section_id";
    public static final String COLUMN_SECTION_NAME = "section_name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_WEB_URL = "url";
    public static final String COLUMN_THUMBNAIL = "thumbnail";
    public static final String COLUMN_AUTHOR = "author";

    private String id;
    private String articleId;
    private String type;
    private String sectionId;
    private String sectionName;
    private String date;
    private String title;
    private String webUrl;
    private String thumbnail;
    private String author;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ARTICLE_ID + " TEXT,"
                    + COLUMN_TYPE + " TEXT,"
                    + COLUMN_SECTION_ID + " TEXT,"
                    + COLUMN_SECTION_NAME + " TEXT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_WEB_URL + " TEXT,"
                    + COLUMN_THUMBNAIL + " TEXT,"
                    + COLUMN_AUTHOR + " TEXT,"
                    + COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SavedArticle() {
    }

    public SavedArticle(String articleId, String type, String sectionId, String sectionName, String date, String title, String webUrl, String thumbnail, String author) {
        this.articleId = articleId;
        this.type = type;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.date = date;
        this.title = title;
        this.webUrl = webUrl;
        this.thumbnail = thumbnail;
        this.author = author;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SavedArticle{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", type='" + type + '\'' +
                ", sectionId='" + sectionId + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
