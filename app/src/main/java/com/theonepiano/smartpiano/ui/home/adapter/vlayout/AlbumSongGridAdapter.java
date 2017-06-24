package com.theonepiano.smartpiano.ui.home.adapter.vlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.theonepiano.smartpiano.R;

/**
 * Created by jim on 2017/6/24.
 */

public class AlbumSongGridAdapter extends DelegateAdapter.Adapter<AlbumSongGridAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private LayoutInflater mLayoutInflater;
    private String mName;

    public AlbumSongGridAdapter(Context context, LayoutHelper helper, String name) {
        mContext = context;
        mLayoutHelper = helper;
        mName = name;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.vlayout_item_home_recommend_album_song, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa3F51B5);
        } else {
            holder.itemView.setBackgroundColor(0xccFF4081);
        }

        MyViewHolder myViewHolder = holder;
        myViewHolder.mName.setText(mName);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutAdapterType.VLAYOUT_ADAPTER_TYPE_ALBUM_SONG;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;

        public MyViewHolder(View itemView) {
            super(itemView);

            mName = (TextView)itemView.findViewById(R.id.item_name);
        }
    }
}
