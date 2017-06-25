package com.theonepiano.smartpiano.ui.guide.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.theonepiano.smartpiano.HomeActivity;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.ui.guide.GuideActivity;

/**
 * Created by jim on 2017/6/4.
 */

public class GuideAdapter extends PagerAdapter {
    private int[] mGuidePages;

    private Context mContext;

    public GuideAdapter(int[] guidePages, Context context) {
        mGuidePages = guidePages;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mGuidePages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_guide, null);
        ImageView guideImageView = (ImageView) view.findViewById(R.id.iv_guide);
        Button guideBtn = (Button) view.findViewById(R.id.btn_guide);

        if (position == mGuidePages.length - 1) {
            guideBtn.setVisibility(View.VISIBLE);
            guideBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, HomeActivity.class));
                    ((GuideActivity) mContext).finish();
                }
            });
        }

        guideImageView.setBackgroundResource(mGuidePages[position]);
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
