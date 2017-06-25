package com.theonepiano.smartpiano.ui.home.fragment.category;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.CategoryContentModel;
import com.theonepiano.smartpiano.presenter.home.impl.CategoryPresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.CategoryContentContract;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.AlbumSongGridAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jim on 2017/6/25.
 */

public class CategoryContentFragment extends BaseFragment<CategoryContentContract.Model, CategoryContentContract.Presenter> implements CategoryContentContract.View {
    @BindView(R.id.category_content_view)
    RecyclerView mContentView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_category_content;
    }

    @Override
    protected void initViews(Bundle bundle) {
        mPresenter = new CategoryPresenter();
        mPresenter.attachModel(new CategoryContentModel());
        mPresenter.attachView(this);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mContentView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        mContentView.setLayoutManager(manager);

        DelegateAdapter delegateAdapter = new DelegateAdapter(manager, true);

        mContentView.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        GridLayoutHelper newAlbumHelper = new GridLayoutHelper(3);
        newAlbumHelper.setAutoExpand(false);
        newAlbumHelper.setWeights(new float[]{33, 33, 33});
        newAlbumHelper.setItemCount(6);
        newAlbumHelper.setAspectRatio(3);

        AlbumSongGridAdapter newAlbumAdapter = new AlbumSongGridAdapter(getContext(), newAlbumHelper, "newest album!");
        adapters.add(newAlbumAdapter);

        delegateAdapter.setAdapters(adapters);
    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @Override
    protected void lazyFetchData() {

    }
}
