package com.theonepiano.smartpiano.ui.home.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.HomeRecommendModel;
import com.theonepiano.smartpiano.presenter.home.impl.HomeRecommendPresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeRecommendContract;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.AlbumSongGridAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.BannerAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.RushKaraColumnLayoutAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.SongItemLinearLayoutAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.TitleLinearLayoutAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jim on 2017/6/13.
 */

public class HomeRecommendFragment extends BaseFragment<HomeRecommendModel, HomeRecommendPresenter> implements HomeRecommendContract.View {
    @BindView(R.id.content_view)
    RecyclerView mContentView;

    public static HomeRecommendFragment getInstance() {
        return new HomeRecommendFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    protected void initViews(Bundle bundle) {
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

        DelegateAdapter delegateAdapter = new DelegateAdapter(manager, true);

        mContentView.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        LinearLayoutHelper bannerHelper = new LinearLayoutHelper();
        bannerHelper.setItemCount(1);
        BannerAdapter bannerAdapter = new BannerAdapter(getContext(), bannerHelper);
        adapters.add(bannerAdapter);

        // rush & kara
        ColumnLayoutHelper categoryHelper = new ColumnLayoutHelper();
        categoryHelper.setItemCount(2);
        categoryHelper.setPadding(20, 20, 20, 20);
        categoryHelper.setMargin(20, 20, 20, 20);
        categoryHelper.setBgColor(Color.GRAY);
        categoryHelper.setAspectRatio(6);
        categoryHelper.setWeights(new float[]{50, 50});

        RushKaraColumnLayoutAdapter rushKaraAdapter = new RushKaraColumnLayoutAdapter(getContext(), categoryHelper, "Kara");
        adapters.add(rushKaraAdapter);

        // hot album's title
        LinearLayoutHelper hotAlbumTitleHelper = new LinearLayoutHelper();
        hotAlbumTitleHelper.setItemCount(1);
        TitleLinearLayoutAdapter hotAlbumTitleAdapter = new TitleLinearLayoutAdapter(getContext(), hotAlbumTitleHelper, "hot album title");
        adapters.add(hotAlbumTitleAdapter);

        // hot album
        GridLayoutHelper hotAlbumHelper = new GridLayoutHelper(3);
        hotAlbumHelper.setAutoExpand(false);
        hotAlbumHelper.setWeights(new float[] {33, 33, 33});
        hotAlbumHelper.setItemCount(6);

        AlbumSongGridAdapter hotAlbumAdapter = new AlbumSongGridAdapter(getContext(), hotAlbumHelper, "Album");
        adapters.add(hotAlbumAdapter);

        // the newest album's title
        LinearLayoutHelper newAlbumTitleHelper = new LinearLayoutHelper();
        newAlbumTitleHelper.setItemCount(1);
        TitleLinearLayoutAdapter newestAlbumTitleAdapter = new TitleLinearLayoutAdapter(getContext(), newAlbumTitleHelper, "The newest album title");
        adapters.add(newestAlbumTitleAdapter);

        // the newest album
        GridLayoutHelper newAlbumHelper = new GridLayoutHelper(3);
        newAlbumHelper.setAutoExpand(false);
        newAlbumHelper.setWeights(new float[] {33, 33, 33});
        newAlbumHelper.setItemCount(3);
        AlbumSongGridAdapter newAlbumAdapter = new AlbumSongGridAdapter(getContext(), newAlbumHelper, "newest album!");
        adapters.add(newAlbumAdapter);

        // the hot songs' title
        LinearLayoutHelper hotSongsTitleHelper = new LinearLayoutHelper();
        hotSongsTitleHelper.setItemCount(1);
        TitleLinearLayoutAdapter hotSongsTitleAdapter = new TitleLinearLayoutAdapter(getContext(), hotSongsTitleHelper, "hot songs title");
        adapters.add(hotSongsTitleAdapter);

        // the hot songs
        LinearLayoutHelper hotSongsHelper = new LinearLayoutHelper();
        hotSongsHelper.setItemCount(5);
        SongItemLinearLayoutAdapter hotSongItemAdapter = new SongItemLinearLayoutAdapter(getContext(), hotSongsHelper, "hot song");
        adapters.add(hotSongItemAdapter);

        // the newest songs' title
        LinearLayoutHelper newSongsTitleHelper = new LinearLayoutHelper();
        newSongsTitleHelper.setItemCount(1);
        TitleLinearLayoutAdapter newSongsTitleAdapter = new TitleLinearLayoutAdapter(getContext(), newSongsTitleHelper, "newest song's title");
        adapters.add(newSongsTitleAdapter);

        // the newest songs
        LinearLayoutHelper newSongsHelper = new LinearLayoutHelper();
        newSongsHelper.setItemCount(100);
        SongItemLinearLayoutAdapter newSongsAdapter = new SongItemLinearLayoutAdapter(getContext(), newSongsHelper, "newest songs!!");
        adapters.add(newSongsAdapter);

        delegateAdapter.setAdapters(adapters);
    }
}
