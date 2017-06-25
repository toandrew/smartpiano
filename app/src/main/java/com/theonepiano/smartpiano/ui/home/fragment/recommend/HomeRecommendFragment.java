package com.theonepiano.smartpiano.ui.home.fragment.recommend;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragment;
import com.theonepiano.smartpiano.base.BaseView;
import com.theonepiano.smartpiano.model.home.HomeRecommendModel;
import com.theonepiano.smartpiano.model.home.bean.Album;
import com.theonepiano.smartpiano.model.home.bean.RecommendAlbumSong;
import com.theonepiano.smartpiano.model.home.bean.Song;
import com.theonepiano.smartpiano.presenter.home.impl.HomeRecommendPresenter;
import com.theonepiano.smartpiano.presenter.home.interfaces.HomeRecommendContract;
import com.theonepiano.smartpiano.ui.home.adapter.HomeRecommendAdapter;

import java.util.ArrayList;
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

        HomeRecommendAdapter delegateAdapter = new HomeRecommendAdapter(getContext(), manager);
        delegateAdapter.update(getHomeRecommendItems());

        mContentView.setAdapter(delegateAdapter);
    }

    private List<RecommendAlbumSong> getHomeRecommendItems() {
        List<RecommendAlbumSong> items = new LinkedList<>();

        RecommendAlbumSong hotAlbum = new RecommendAlbumSong();
        hotAlbum.type = RecommendAlbumSong.TYPE_ALBUM;
        hotAlbum.title = "hot albums";
        hotAlbum.albums = getAlbums();

        items.add(hotAlbum);

        RecommendAlbumSong newestAlbum = new RecommendAlbumSong();
        newestAlbum.type = RecommendAlbumSong.TYPE_ALBUM;
        newestAlbum.title = "newest albums";
        newestAlbum.albums = getAlbums();

        items.add(newestAlbum);

        RecommendAlbumSong hotSong = new RecommendAlbumSong();
        hotSong.type = RecommendAlbumSong.TYPE_SONG;
        hotSong.title = "hot songs";
        hotSong.songs = getSongs();

        items.add(hotSong);

        RecommendAlbumSong newestSong = new RecommendAlbumSong();
        newestSong.type = RecommendAlbumSong.TYPE_SONG;
        newestSong.title = "newest songs";
        newestSong.songs = getSongs();

        items.add(newestSong);

        return items;
    }

    private List<Album> getAlbums() {
        List<Album> albumList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            Album album = new Album();
            albumList.add(album);
        }

        return albumList;
    }

    private List<Song> getSongs() {
        List<Song> songList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Song song = new Song();
            songList.add(song);
        }

        return songList;
    }
}
