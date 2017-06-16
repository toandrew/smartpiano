package com.theonepiano.smartpiano.ui.guide;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragmentActivity;
import com.theonepiano.smartpiano.ui.guide.adapter.GuideAdapter;

import butterknife.BindView;

/**
 * Created by jim on 2017/5/27.
 */

public class GuideActivity extends BaseFragmentActivity {

    @BindView(R.id.guide)
    ViewPager mViewPager;

    private int[] mGuidePages = new int[]{R.drawable.guide_bg1, R.drawable.guide_bg2, R.drawable.guide_bg3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void init() {
        GuideAdapter adapter = new GuideAdapter(mGuidePages, this);
        mViewPager.setAdapter(adapter);
    }
}
