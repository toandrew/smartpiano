package com.theonepiano.smartpiano.api.home;

import com.theonepiano.smartpiano.model.bean.BaseListBean;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jim on 2017/6/18.
 */

public interface HomeService {
    @GET("/smart-piano/v4/recommend")
    Observable<List<BaseListBean>> getRecommend();
}
