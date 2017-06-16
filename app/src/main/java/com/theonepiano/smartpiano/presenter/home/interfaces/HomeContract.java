package com.theonepiano.smartpiano.presenter.home.interfaces;

import com.theonepiano.smartpiano.base.BaseModel;
import com.theonepiano.smartpiano.base.BasePresenter;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.bean.HomeCateList;

import java.util.List;

import rx.Observable;

/**
 * Created by jim on 2017/6/12.
 */

public interface HomeContract {
    interface View extends BaseView {
        void onGetHomeAllList(List<HomeCateList> cateList);
    }

    interface Model extends BaseModel {
        Observable<List<HomeCateList>> getHomeCateList();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHomeCateList();
    }
}
