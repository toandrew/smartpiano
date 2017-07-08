package com.theonepiano.smartpiano.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jim on 2017/7/8.
 */

public class Album extends BaseListBean {
    @SerializedName("cover_image_url")
    @Expose
    private String coverImageUrl;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_en")
    @Expose
    private Object descriptionEn;
    @SerializedName("description_zh")
    @Expose
    private Object descriptionZh;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("region_cn")
    @Expose
    private String regionCn;
    @SerializedName("region_us")
    @Expose
    private String regionUs;
    @SerializedName("updated_at")
    @Expose
    private Integer updatedAt;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(Object descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Object getDescriptionZh() {
        return descriptionZh;
    }

    public void setDescriptionZh(Object descriptionZh) {
        this.descriptionZh = descriptionZh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionCn() {
        return regionCn;
    }

    public void setRegionCn(String regionCn) {
        this.regionCn = regionCn;
    }

    public String getRegionUs() {
        return regionUs;
    }

    public void setRegionUs(String regionUs) {
        this.regionUs = regionUs;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
