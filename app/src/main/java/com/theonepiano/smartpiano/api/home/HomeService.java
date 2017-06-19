package com.theonepiano.smartpiano.api.home;

import com.theonepiano.smartpiano.model.home.bean.HomeCateList;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jim on 2017/6/18.
 */

public interface HomeService {
    @GET("home")
    Observable<List<HomeCateList>> getHomeCateList();
}
