package com.theonepiano.smartpiano;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.theonepiano.smartpiano.base.BaseFragmentActivity;
import com.theonepiano.smartpiano.ui.guide.GuideActivity;
import com.theonepiano.smartpiano.ui.splash.LangLangActivity;

import butterknife.BindView;

public class MainActivity extends BaseFragmentActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String PREFERENCE_NAME = "Once";
    private static final String FIRST_OPEN_TAG = "first_open";
    private static final String LANGLANG_OPEN_TAG = "langlang_open";

    private static final int LOGO_DISPLAY_TIMEOUT_MS = 600;
    private static final int LOGO_ENTER_ANOTHER_ACTIVITY_TIMEOUT_MS = 1000;

    @BindView(R.id.logo)
    ImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        delayShow();
    }

    @VisibleForTesting
    void delayShow() {
        animBg(mLogo);

        mLogo.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFinishing()) {
                    return;
                }
                mLogo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isFinishing()) {
                            return;
                        }
                        displayScene();
                    }
                }, LOGO_ENTER_ANOTHER_ACTIVITY_TIMEOUT_MS);
            }
        }, LOGO_DISPLAY_TIMEOUT_MS);
    }

    @VisibleForTesting
    boolean isLangLangFirst() {
        SharedPreferences prefs = MainActivity.this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(LANGLANG_OPEN_TAG, true);
    }

    @VisibleForTesting
    void setLangLangFirst(boolean first) {
        SharedPreferences prefs = MainActivity.this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(LANGLANG_OPEN_TAG, first).apply();
    }

    @VisibleForTesting
    boolean isFirst() {
        SharedPreferences prefs = MainActivity.this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(FIRST_OPEN_TAG, true);
    }

    @VisibleForTesting
    void setFirst(boolean first) {
        SharedPreferences prefs = MainActivity.this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(FIRST_OPEN_TAG, first).apply();
    }

    @VisibleForTesting
    void displayScene() {
        Log.w(TAG, "displayScene!!!!" + isFirst());

        animLogo(mLogo);

        if (isFirst()) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);

            setFirst(false);
        } else {
            if (isLangLangFirst()) {
                Intent intent = new Intent(this, LangLangActivity.class);
                startActivity(intent);

                setLangLangFirst(false);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }

        finish();
    }

    @VisibleForTesting
    void animBg(View view) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "alpha",
                1f, 0.3f);
        //四个动画同时执行
        anim1.setDuration(3000);
        anim1.start();
    }

    @VisibleForTesting
    void animLogo(View view) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "alpha",
                0f, 1.0f);
        anim1.setDuration(2400);
        anim1.start();
    }

}
