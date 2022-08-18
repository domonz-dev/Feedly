package com.domonz.swipe;

public class FeedModel {

    String title, category, imgUrl ,webName, webUrl;
    float popularity;

    public FeedModel(String title, String category, String imgUrl, String webName, String webUrl, float popularity) {
        this.title = title;
        this.category = category;
        this.imgUrl = imgUrl;
        this.webName = webName;
        this.webUrl = webUrl;
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }
}
