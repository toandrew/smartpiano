package com.theonepiano.smartpiano.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.theonepiano.smartpiano.model.ContractProxy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * Created by jim on 2017/6/25.
 */

public abstract class BaseSwipeBackActivity<M extends BaseModel, P extends BasePresenter> extends AppCompatActivity {
    private static final int MODEL_CLASS_INDEX = 0;
    private static final int PRESENTER_CLASS_INDEX = 1;

    protected P mPresenter;

    protected Unbinder mUnbinder;

    private SwipeBackLayout mSwipeBackLayout;

    protected abstract int getLayoutId();

    protected abstract void initViews(Bundle bundle);

    protected abstract BaseView getViewImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            processSavedInstanceState();
        }

        initWindows();

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());

            mUnbinder = ButterKnife.bind(this);
            bindMVP();
            initViews(savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        if (mPresenter != null) {
            ContractProxy.getInstance().unBindView(getViewImpl(), mPresenter);
            ContractProxy.getInstance().unBindModel(getModelClazz(), mPresenter);
        }
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipeBackLayout.attachToActivity(this);
    }

    @Override
    public View findViewById(int id) {
        View view = super.findViewById(id);
        if (view == null && mSwipeBackLayout != null) {
            return mSwipeBackLayout.findViewById(id);
        }

        return view;
    }

    protected Class getModelClazz() {
        return (Class<M>) ContractProxy.getModelClazz(getClass(), MODEL_CLASS_INDEX);
    }

    protected Class getPresenterClazz() {
        return (Class<P>) ContractProxy.getPresenterClazz(getClass(), PRESENTER_CLASS_INDEX);
    }

    private void processSavedInstanceState() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (fragments != null && fragments.size() > 0) {
            boolean show = false;

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            for (int i = fragments.size() - 1; i >= 0; i--) {
                Fragment fragment = fragments.get(i);
                if (fragment != null) {
                    if (!show) {
                        ft.show(fragment);
                        show = true;
                    } else {
                        ft.hide(fragment);
                    }
                }
            }

            ft.commit();
        }
    }

    private void initWindows() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getDecorView().setBackgroundDrawable(null);

        mSwipeBackLayout = new SwipeBackLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSwipeBackLayout.setLayoutParams(params);
    }

    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            bindVM();
        }
    }

    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }

    private void bindVM() {
        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getViewImpl() != null) {
            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
            ContractProxy.getInstance().bindView(getViewImpl(), mPresenter);
        }
    }
}
