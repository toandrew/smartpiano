package com.theonepiano.smartpiano.model.home;

import com.theonepiano.smartpiano.api.RestClient;
import com.theonepiano.smartpiano.model.home.bean.HomeCateList;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeContract;

import java.util.List;

import rx.Observable;

/**
 * Created by jim on 2017/6/12.
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<List<HomeCateList>> getHomeCateList() {
        return RestClient.getInstance().getHomeService().getHomeCateList();
    }
}
