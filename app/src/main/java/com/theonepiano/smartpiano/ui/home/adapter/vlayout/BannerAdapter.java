package com.theonepiano.smartpiano.ui.home.adapter.vlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.view.home.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.Arrays;

/**
 * Created by jim on 2017/6/24.
 */

public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.MyViewHolder>{
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private LayoutInflater mLayoutInflater;

    public BannerAdapter(Context context, LayoutHelper helper) {
        mContext = context;
        mLayoutHelper = helper;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.w("HAHA", "BannerAdapter: onCreateViewHolder!");
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.vlayout_item_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return VlayoutAdapterType.VLAYOUT_ADAPTER_TYPE_BANNER;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private Banner mBanner;

        public MyViewHolder(View itemView) {
            super(itemView);

            mBanner = (Banner)itemView.findViewById(R.id.banner);

            initBanner();
        }

        private void initBanner() {
            Integer[] images = {R.drawable.guide_bg1, R.drawable.guide_bg2, R.drawable.guide_bg3};
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setImageLoader(new GlideImageLoader());
            mBanner.setImages(Arrays.asList(images));
            mBanner.setBannerAnimation(Transformer.DepthPage);
            mBanner.isAutoPlay(true);
            mBanner.setDelayTime(1500);
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.start();
        }
    }

}
