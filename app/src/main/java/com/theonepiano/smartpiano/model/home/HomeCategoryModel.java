package com.theonepiano.smartpiano.model.home;

import com.theonepiano.smartpiano.model.home.bean.HomeCategoryGenreList;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeCategoryContract;

import java.util.List;

import rx.Observable;

/**
 * Created by jim on 2017/6/13.
 */

public class HomeCategoryModel implements HomeCategoryContract.Model {
    @Override
    public Observable<List<HomeCategoryGenreList>> getHomeCategoryGenreList() {
        return null;
    }
}
