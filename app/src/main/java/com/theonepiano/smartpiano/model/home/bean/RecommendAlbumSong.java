package com.theonepiano.smartpiano.model.home.bean;

import com.theonepiano.smartpiano.model.bean.Album;
import com.theonepiano.smartpiano.model.bean.Song;

import java.util.List;

/**
 * Created by jim on 2017/6/25.
 */

public class RecommendAlbumSong {
    public static final int TYPE_SONG = 2;
    public static final int TYPE_ALBUM = 1;

    public int type; // album or song

    public String title;

    public List<Album> albums;

    public List<Song> songs;

    public String getTitle() {
        return title;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
