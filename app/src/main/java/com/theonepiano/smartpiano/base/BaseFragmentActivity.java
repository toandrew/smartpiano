package com.theonepiano.smartpiano.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jim on 2017/5/27.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        mUnbinder = ButterKnife.bind(this);

        init();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        super.onDestroy();
    }

    public abstract int getLayoutId();

    public abstract void init();
}
