package com.theonepiano.smartpiano.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jim on 2017/7/8.
 */

public class Song extends BaseListBean {
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("copyright_cn")
    @Expose
    private String copyrightCn;
    @SerializedName("copyright_us")
    @Expose
    private String copyrightUs;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("deleted_seq")
    @Expose
    private Integer deletedSeq;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kara")
    @Expose
    private Kara kara;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("only_support_88")
    @Expose
    private Integer onlySupport88;
    @SerializedName("region_cn")
    @Expose
    private String regionCn;
    @SerializedName("region_us")
    @Expose
    private String regionUs;
    @SerializedName("rush")
    @Expose
    private Rush rush;
    @SerializedName("score")
    @Expose
    private Score score;
    @SerializedName("song_id")
    @Expose
    private Integer songId;
    @SerializedName("updated_at")
    @Expose
    private Integer updatedAt;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;
    @SerializedName("video")
    @Expose
    private List<Video> video = null;
}
