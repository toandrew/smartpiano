package com.theonepiano.smartpiano.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theonepiano.smartpiano.model.ContractProxy;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jim on 2017/6/11.
 */

public abstract class BaseFragment<M extends BaseModel, P extends BasePresenter> extends Fragment {
    private static final int MODEL_CLASS_INDEX = 0;
    private static final int PRESENTER_CLASS_INDEX = 1;

    protected Unbinder mUnbinder;

    protected P mPresenter;

    // data is loaded!
    private boolean mHasFetchedData;

    // view is ok!
    private boolean mIsViewPrepared;

    protected abstract int getLayoutId();

    protected abstract void initViews(Bundle bundle);

    protected abstract BaseView getViewImpl();

    // Lazy load data in different fragments
    protected abstract void lazyFetchData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        mUnbinder = ButterKnife.bind(this, view);

        bindMVP();

        initViews(savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIsViewPrepared = true;

        lazyFetchDataIfNeeded();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            lazyFetchDataIfNeeded();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        if (mPresenter != null) {
            ContractProxy.getInstance().unBindView(getViewImpl(), mPresenter);
            ContractProxy.getInstance().unBindModel(getModelClazz(), mPresenter);
        }
    }

    protected Class getModelClazz() {
        return (Class<M>) ContractProxy.getModelClazz(getClass(), MODEL_CLASS_INDEX);
    }

    protected Class getPresenterClass() {
        return (Class<P>) ContractProxy.getPresenterClazz(getClass(), PRESENTER_CLASS_INDEX);
    }

    /**
     * Bind M,V,P
     */
    private void bindMVP() {
        if (getPresenterClass() != null) {
            mPresenter = getPresenterImpl();

            bindVM();
        }
    }

    private void bindVM() {
        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getViewImpl() != null) {
            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
            ContractProxy.getInstance().bindView(getViewImpl(), mPresenter);
        }
    }

    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClass());
    }

    /**
     * Check whether we need to lazy load data
     */
    private void lazyFetchDataIfNeeded() {
        if (getUserVisibleHint() && !mHasFetchedData && mIsViewPrepared) {
            mHasFetchedData = true; // TODO

            lazyFetchData();
        }
    }
}
