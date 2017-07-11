package com.theonepiano.smartpiano.ui.home.fragment.category;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.bean.Album;
import com.theonepiano.smartpiano.model.home.HomeCategoryContentModel;
import com.theonepiano.smartpiano.presenter.home.impl.HomeCategoryContentPresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeCategoryContentContract;
import com.theonepiano.smartpiano.ui.home.adapter.HomeCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jim on 2017/6/25.
 */

public class HomeCategoryContentFragment extends BaseFragment<HomeCategoryContentContract.Model, HomeCategoryContentContract.Presenter> implements HomeCategoryContentContract.View {
    @BindView(R.id.category_content_view)
    RecyclerView mContentView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_category_content;
    }

    @Override
    protected void initViews(Bundle bundle) {
        mPresenter = new HomeCategoryContentPresenter();
        mPresenter.attachModel(new HomeCategoryContentModel());
        mPresenter.attachView(this);

        initContentViews();
    }

    @Override
    protected BaseView getViewImpl() {
        return this;
    }

    @Override
    protected void lazyFetchData() {

    }

    private void initContentViews() {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mContentView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        mContentView.setLayoutManager(manager);

        HomeCategoryAdapter delegateAdapter = new HomeCategoryAdapter(getContext(), manager);
        delegateAdapter.update(getHomeCategoryAlbums());

        mContentView.setAdapter(delegateAdapter);
    }

    private List<Album> getHomeCategoryAlbums() {
        List<Album> albums = new ArrayList<>();
        albums.addAll(getAlbums());

        return albums;
    }

    private List<Album> getAlbums() {
        List<Album> albumList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Album album = new Album();
            albumList.add(album);
        }

        return albumList;
    }
}
