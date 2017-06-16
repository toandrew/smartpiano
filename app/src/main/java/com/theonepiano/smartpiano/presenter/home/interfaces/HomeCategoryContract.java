package com.theonepiano.smartpiano.presenter.home.interfaces;

import com.theonepiano.smartpiano.base.BaseModel;
import com.theonepiano.smartpiano.base.BasePresenter;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.bean.HomeCategoryGenreList;

import java.util.List;

import rx.Observable;

/**
 * Created by jim on 2017/6/13.
 */

public interface HomeCategoryContract {
    interface View extends BaseView {
        void onGetCategoryGenreList(List<HomeCategoryGenreList> genreList);
    }

    interface Model extends BaseModel {
        Observable<List<HomeCategoryGenreList>> getHomeCategoryGenreList();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHomeCategoryGenreList();
    }
}
