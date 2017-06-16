package com.theonepiano.smartpiano.presenter.home.impl;

import com.theonepiano.smartpiano.model.home.bean.HomeCateList;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jim on 2017/6/12.
 */

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void getHomeCateList() {
        List<HomeCateList> list = new ArrayList<>();
        list.add(new HomeCateList());
        list.add(new HomeCateList());
        mView.onGetHomeAllList(list);
    }
}
