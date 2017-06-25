package com.theonepiano.smartpiano.ui.home.adapter;

import android.content.Context;
import android.graphics.Color;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.theonepiano.smartpiano.model.home.bean.Album;
import com.theonepiano.smartpiano.model.home.bean.RecommendAlbumSong;
import com.theonepiano.smartpiano.model.home.bean.Song;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.AlbumSongGridAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.BannerAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.RushKaraColumnLayoutAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.SongItemLinearLayoutAdapter;
import com.theonepiano.smartpiano.ui.home.adapter.vlayout.TitleLinearLayoutAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jim on 2017/6/25.
 */

public class HomeRecommendAdapter extends DelegateAdapter {
    List<Adapter> mAdapters;
    private Context mContext;
    private BannerAdapter mBannerAdapter;

    public HomeRecommendAdapter(Context context, VirtualLayoutManager layoutManager) {
        super(layoutManager, true);

        mContext = context;

        mAdapters = new LinkedList<>();
    }

    public void update(List<RecommendAlbumSong> items) {
        mAdapters.clear();

        initBanner();

        initRushAndKara();

        initAlbumsAndSongs(items);

        this.setAdapters(mAdapters);
    }

    private void initBanner() {
        LinearLayoutHelper bannerHelper = new LinearLayoutHelper();
        bannerHelper.setItemCount(1);
        mBannerAdapter = new BannerAdapter(mContext, bannerHelper);
        mAdapters.add(mBannerAdapter);
    }

    private void initRushAndKara() {
        ColumnLayoutHelper categoryHelper = new ColumnLayoutHelper();
        categoryHelper.setItemCount(2);
        categoryHelper.setPadding(20, 20, 20, 20);
        categoryHelper.setMargin(20, 20, 20, 20);
        categoryHelper.setBgColor(Color.GRAY);
        categoryHelper.setAspectRatio(6);
        categoryHelper.setWeights(new float[]{50, 50});

        RushKaraColumnLayoutAdapter rushKaraAdapter = new RushKaraColumnLayoutAdapter(mContext, categoryHelper, "Kara");
        mAdapters.add(rushKaraAdapter);
    }

    private void initAlbumsAndSongs(List<RecommendAlbumSong> items) {
        for (RecommendAlbumSong item : items) {
            if (item.type == RecommendAlbumSong.TYPE_ALBUM) {
                initAlbums(item.getTitle(), item.getAlbums());
            } else if (item.type == RecommendAlbumSong.TYPE_SONG) {
                initSongs(item.getTitle(), item.getSongs());
            }
        }
    }

    private void initAlbums(String title, List<Album> albums) {
        createTitleHelper(title);

        // create album
        GridLayoutHelper albumHelper = new GridLayoutHelper(3);
        albumHelper.setAutoExpand(false);
        albumHelper.setWeights(new float[]{33, 33, 33});
        albumHelper.setItemCount(3);
        albumHelper.setAspectRatio(3);

        AlbumSongGridAdapter albumAdapter = new AlbumSongGridAdapter(mContext, albumHelper, title, albums);
        mAdapters.add(albumAdapter);
    }

    private void initSongs(String title, List<Song> songs) {
        createTitleHelper(title);

        LinearLayoutHelper songHelper = new LinearLayoutHelper();
        songHelper.setItemCount(songs.size());
        SongItemLinearLayoutAdapter songAdapter = new SongItemLinearLayoutAdapter(mContext, songHelper, title, songs);

        mAdapters.add(songAdapter);
    }

    private void createTitleHelper(String title) {
        LinearLayoutHelper titleHelper = new LinearLayoutHelper();
        titleHelper.setItemCount(1);
        TitleLinearLayoutAdapter titleAdapter = new TitleLinearLayoutAdapter(mContext, titleHelper, title);

        mAdapters.add(titleAdapter);
    }
}
