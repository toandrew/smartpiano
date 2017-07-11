package com.theonepiano.smartpiano.presenter.course.interfaces;

import com.theonepiano.smartpiano.base.BaseModel;
import com.theonepiano.smartpiano.base.BasePresenter;
import com.theonepiano.smartpiano.base.BaseView;

/**
 * Created by jim on 2017/7/11.
 */

public interface CourseContract {
    interface View extends BaseView {
    }

    interface Model extends BaseModel {
    }

    abstract class Presenter extends BasePresenter<View, Model> {
    }
}
