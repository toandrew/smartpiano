package com.theonepiano.smartpiano.ui.home.adapter;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/**
 * Created by jim on 2017/6/25.
 */

public class HomeRecommendAdapter extends DelegateAdapter {
    public HomeRecommendAdapter(VirtualLayoutManager layoutManager) {
        this(layoutManager, false);
    }

    public HomeRecommendAdapter(VirtualLayoutManager layoutManager, boolean hasConsistItemType) {
        super(layoutManager, hasConsistItemType);

        init();
    }

    private void init() {
    }
}
