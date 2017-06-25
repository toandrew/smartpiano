package com.theonepiano.smartpiano.ui.home.fragment.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.HomeCategoryModel;
import com.theonepiano.smartpiano.model.home.bean.HomeCategoryGenreList;
import com.theonepiano.smartpiano.presenter.home.impl.HomeCategoryPresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeCategoryContract;
import com.theonepiano.smartpiano.view.home.CategoryFilterView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by jim on 2017/6/13.
 */

public class HomeCategoryFragment extends BaseFragment<HomeCategoryModel, HomeCategoryPresenter> implements HomeCategoryContract.View {
    @BindView(R.id.category_filter_view)
    CategoryFilterView mCategoryFilterView;

    public static HomeCategoryFragment getInstance() {
        HomeCategoryFragment fragment = new HomeCategoryFragment();

        // TODO

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_category;
    }

    @Override
    protected void initViews(Bundle bundle) {
        initFilterContentView();
    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getHomeCategoryGenreList();
    }

    @Override
    public void onGetCategoryGenreList(List<HomeCategoryGenreList> genreList) {
        mCategoryFilterView.updateGenresItem(genreList);
    }

    private void initFilterContentView() {
        mCategoryFilterView.setContentView(getContentView());
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_category_content, null);
        return contentView;
    }
}
