package com.theonepiano.smartpiano.base;

/**
 * Created by jim on 2017/6/11.
 */

public interface Presenter<View, Model> {
    /**
     * Called when attaching view
     *
     * @param view
     */
    void attachView(View view);

    /**
     * Called when attaching model
     *
     * @param model
     */
    void attachModel(Model model);

    /**
     * Called when detaching view
     */
    void detachView();

    /**
     * Called when detaching model
     */
    void detachModel();
}
