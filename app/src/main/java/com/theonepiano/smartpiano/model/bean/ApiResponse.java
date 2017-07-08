package com.theonepiano.smartpiano.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jim on 2017/7/8.
 */

public class ApiResponse<T> {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private T data;

    public Meta getMeta() {
        return meta;
    }

    public T getData() {
        return data;
    }

    public static class Meta {
        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("message")
        @Expose
        private String message;

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
