package com.theonepiano.smartpiano.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jim on 2017/6/11.
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> implements Presenter<V, M> {
    protected V mView;

    protected M mModel;

    CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void attachModel(M model) {
        mModel = model;
    }

    @Override
    public void detachView() {
        mView = null;

        unSubscribe();
    }

    @Override
    public void detachModel() {
        mModel = null;
    }

    public M getModel() {
        return mModel;
    }

    public V getView() {
        return mView;
    }

    public boolean isViewBind() {
        return mView != null;
    }

    protected void addSubscribe(Subscription s) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }

        mCompositeSubscription.add(s);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
