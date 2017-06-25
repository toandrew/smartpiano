package com.theonepiano.smartpiano.ui.home.adapter.vlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.ui.home.activity.HomeMoreAlbumActivity;

/**
 * Created by jim on 2017/6/24.
 */

public class TitleLinearLayoutAdapter extends DelegateAdapter.Adapter<TitleLinearLayoutAdapter.MyViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private String mName;

    private LayoutInflater mLayoutInflater;

    public TitleLinearLayoutAdapter(Context context, LayoutHelper helper, String name) {
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
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.vlayout_item_title, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(0xaaff6666);

        final MyViewHolder myViewHolder = holder;
        myViewHolder.mName.setText(mName);

        myViewHolder.mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HomeMoreAlbumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", myViewHolder.mName.getText().toString());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutAdapterType.VLAYOUT_ADAPTER_TYPE_TITLE;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mMore;

        public MyViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.item_name);
            mMore = (TextView) itemView.findViewById(R.id.item_more);
        }
    }
}
