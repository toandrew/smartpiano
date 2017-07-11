package com.theonepiano.smartpiano.ui.course.fragment;

import android.os.Bundle;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.course.CourseModel;
import com.theonepiano.smartpiano.presenter.course.impl.CoursePresenter;
import com.theonepiano.smartpiano.presenter.course.interfaces.CourseContract;

/**
 * Created by jim on 2017/6/12.
 */

public class CourseFragment extends BaseFragment<CourseModel, CoursePresenter> implements CourseContract.View {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initViews(Bundle bundle) {

    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @Override
    protected void lazyFetchData() {

    }
}
