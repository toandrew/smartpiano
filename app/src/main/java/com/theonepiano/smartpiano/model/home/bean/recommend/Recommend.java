package com.theonepiano.smartpiano.model.home.bean.recommend;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.theonepiano.smartpiano.model.bean.Album;
import com.theonepiano.smartpiano.model.bean.BaseListBean;
import com.theonepiano.smartpiano.model.bean.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jim on 2017/7/8.
 */

public class Recommend {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("list")
    @Expose
    private JsonArray items = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Integer type;

    List<BaseListBean> getItems() {
        List<BaseListBean> beans = new ArrayList<>();

        Gson gson = new Gson();
        for (int i = 0; i < items.size(); i++) {
            if (isAlbum()) {
                Album model = gson.fromJson(items.get(i), Album.class);
                beans.add(model);
            } else {
                Song model = gson.fromJson(items.get(i), Song.class);
                beans.add(model);
            }
        }

        return beans;
    }

    private boolean isAlbum() {
        return  type.intValue() == 1;
    }
}
