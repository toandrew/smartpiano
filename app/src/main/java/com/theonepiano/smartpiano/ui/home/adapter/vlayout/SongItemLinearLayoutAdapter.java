package com.theonepiano.smartpiano.ui.home.adapter.vlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.model.bean.Song;

import java.util.List;

/**
 * Created by jim on 2017/6/24.
 */

public class SongItemLinearLayoutAdapter extends DelegateAdapter.Adapter<SongItemLinearLayoutAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private String mName;

    private List<Song> songs;

    private LayoutInflater mLayoutInflater;

    public SongItemLinearLayoutAdapter(Context context, LayoutHelper helper, String name, List<Song> songs) {
        mContext = context;
        mLayoutHelper = helper;
        mName = name;

        this.songs = songs;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.w("HAHA", "onCreateViewHolder!!!!");
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.vlayout_item_song, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(0xcccccc99);

        MyViewHolder myViewHolder = holder;
        myViewHolder.mName.setText(mName + position);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutAdapterType.VLAYOUT_ADAPTER_TYPE_SONG_ITEM;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;

        public MyViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
