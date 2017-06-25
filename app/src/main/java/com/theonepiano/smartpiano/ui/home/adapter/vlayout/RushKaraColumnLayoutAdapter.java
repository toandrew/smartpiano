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

public class RushKaraColumnLayoutAdapter extends DelegateAdapter.Adapter<RushKaraColumnLayoutAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private String mName;

    private LayoutInflater mLayoutInflater;

    public RushKaraColumnLayoutAdapter(Context context, LayoutHelper helper, String name) {
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
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.vlayout_item_home_recommend_kara_rush, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == 0) {
            holder.itemView.setBackgroundColor(0xaaff6666);
        } else {
            holder.itemView.setBackgroundColor(0xcccccc99);
        }

        MyViewHolder myViewHolder = holder;
        myViewHolder.mTextView.setText(mName);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutAdapterType.VLAYOUT_ADAPTER_TYPE_RUSH_KARA;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public MyViewHolder(View view) {
            super(view);

            mTextView = (TextView) view.findViewById(R.id.fix_name);
        }
    }
}
