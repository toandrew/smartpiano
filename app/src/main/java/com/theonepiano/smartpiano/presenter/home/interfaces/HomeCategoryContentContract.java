package com.theonepiano.smartpiano.presenter.home.interfaces;

import com.theonepiano.smartpiano.base.BaseModel;
import com.theonepiano.smartpiano.base.BasePresenter;
import com.theonepiano.smartpiano.base.BaseView;

/**
 * Created by jim on 2017/6/25.
 */

public interface HomeCategoryContentContract {
    interface View extends BaseView {
    }

    interface Model extends BaseModel {
    }

    abstract class Presenter extends BasePresenter<View, Model> {
    }
}
