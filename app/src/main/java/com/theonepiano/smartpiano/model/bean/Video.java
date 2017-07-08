package com.theonepiano.smartpiano.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jim on 2017/7/8.
 */

public class Video {
    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("url")
    @Expose
    private String url;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
