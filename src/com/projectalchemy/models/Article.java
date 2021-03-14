package com.projectalchemy.models;

import com.projectalchemy.util.MD5Hash;

import java.sql.Date;

public class Article {


    private String id;
    private String title;
    private String details;
    private String rawDetails;
    private Date timePublished;
    private Date timeUpdated;
    private String url;
    private String category;

    public String getRawDetails() {
        return rawDetails;
    }

    public void setRawDetails(String rawDetails) {
        this.rawDetails = rawDetails;
    }

    public String getDetails() {
        ///System.out.println(details);
        return details;
    }

    public Date getTimePublished() {
        return timePublished;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    //TIme publish added
    public void setTimePublished(Date timePublished) {
        this.timePublished = timePublished;
    }

    ///It is used for store update time
    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public void setUrl(String url) {
        this.url = url;

        try {
            this.id = MD5Hash.getHash(url);
        } catch (Exception exception) {

        }
    }

    public String getId() throws Exception {
        if (this.url == null) throw new Exception("Url not set yet;");
        return id;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
