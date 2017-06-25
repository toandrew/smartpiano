package com.theonepiano.smartpiano.model;

import com.theonepiano.smartpiano.base.BaseModel;
import com.theonepiano.smartpiano.base.BasePresenter;
import com.theonepiano.smartpiano.base.BaseView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by jim on 2017/6/13.
 */

public class ContractProxy {
    private static final ContractProxy sInstance = new ContractProxy();

    private ContractProxy() {
    }

    public static ContractProxy getInstance() {
        return sInstance;
    }

    /**
     * Get Real Model's class
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class<BaseModel> getModelClazz(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return BaseModel.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index > params.length || index < 0) {
            return BaseModel.class;
        }

        if (!(params[index] instanceof Class)) {
            return BaseModel.class;
        }

        return (Class) params[index];
    }

    /**
     * Get Real Presenter's class
     *
     * @param clazz
     * @param index
     * @return
     */
    public static Class<BasePresenter> getPresenterClazz(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return BasePresenter.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index > params.length || index < 0) {
            return BasePresenter.class;
        }

        if (!(params[index] instanceof Class)) {
            return BasePresenter.class;
        }

        return (Class) params[index];
    }

    /**
     * Create a presenter
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T presenter(Class clazz) {
        BasePresenter presenter = null;
        try {
            presenter = (BasePresenter) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) presenter;
    }

    public <M> M bindModel(Class modelClazz, BasePresenter presenter) {
        BaseModel model = null;
        try {
            model = (BaseModel) modelClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (model != presenter.getModel()) {
            if (presenter.getModel() != null) {
                presenter.detachModel();
            }

            presenter.attachModel(model);
        }

        return (M) model;
    }

    public <V> V bindView(BaseView view, BasePresenter presenter) {
        if (view != presenter.getView()) {
            if (presenter.getView() != null) {
                presenter.detachView();
            }

            presenter.attachView(view);
        }

        return (V) view;
    }

    public <P extends BasePresenter> void unBindView(BaseView view, P presenter) {
        if (view != presenter.getView()) {
            if (presenter.getView() != null) {
                presenter.detachView();
            }
        }
    }

    public <P extends BasePresenter> void unBindModel(Class clazz, P presenter) {
        if (presenter.getModel() != null) {
            presenter.detachModel();
        }
    }

}
