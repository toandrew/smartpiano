package com.theonepiano.smartpiano.presenter.home.impl;

import com.theonepiano.smartpiano.presenter.home.interfaces.HomeCategoryContract;

/**
 * Created by jim on 2017/6/13.
 */

public class HomeCategoryPresenter extends HomeCategoryContract.Presenter {
    @Override
    public void getHomeCategoryGenreList() {
        mView.onGetCategoryGenreList(null);
    }
}
