package com.hqinterview.dhara.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by USER on 23-07-2015.
 */
public class CommonHolder implements Serializable{
    @SerializedName("url")
    private String url;
    @SerializedName("filePath")
    private String filePath;
    @SerializedName("namespace")
    private String namespace;
    @SerializedName("cache")
    private String cache;
    @SerializedName("pageTitle")
    private String pageTitle;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
