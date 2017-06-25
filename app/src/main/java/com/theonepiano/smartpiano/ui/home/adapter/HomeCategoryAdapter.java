package com.theonepiano.smartpiano.ui.home.adapter;

import android.content.Context;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.theonepiano.smartpiano.model.home.bean.Album;
import com.theonepiano.smartpiano.model.home.bean.RecommendAlbumSong;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.AlbumSongGridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jim on 2017/6/25.
 */

public class HomeCategoryAdapter extends DelegateAdapter {
    List<Adapter> mAdapters;

    private Context mContext;

    public HomeCategoryAdapter(Context context, VirtualLayoutManager layoutManager) {
        super(layoutManager, true);

        mContext = context;

        mAdapters = new ArrayList<>();
    }

    public void update(List<Album> albums) {
        mAdapters.clear();

        initAlbums(albums);

        this.setAdapters(mAdapters);
    }

    private void initAlbums(List<Album> albums) {
        // create album
        GridLayoutHelper albumHelper = new GridLayoutHelper(3);
        albumHelper.setAutoExpand(false);
        albumHelper.setWeights(new float[]{33, 33, 33});
        albumHelper.setItemCount(3);
        albumHelper.setAspectRatio(3);

        AlbumSongGridAdapter albumAdapter = new AlbumSongGridAdapter(mContext, albumHelper, "", albums);
        mAdapters.add(albumAdapter);
    }
}
