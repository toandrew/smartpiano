package com.theonepiano.smartpiano.ui.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.theonepiano.smartpiano.HomeActivity;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.base.BaseFragmentActivity;
import com.yqritc.scalablevideoview.ScalableType;
import com.yqritc.scalablevideoview.ScalableVideoView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jim on 2017/5/26.
 */

public class LangLangActivity extends BaseFragmentActivity {

    @BindView(R.id.video_view)
    ScalableVideoView mVideoView;

    @BindView(R.id.start_btn)
    Button mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_langlang;
    }

    @Override
    public void init() {
        mStartBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f);
        mStartBtn.requestFocus();

        try {
            mVideoView.setRawData(R.raw.langlang);
            mVideoView.setScalableType(ScalableType.FIT_CENTER);
            mVideoView.prepareAsync(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // Only start play after prepared.
                    mVideoView.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.start_btn)
    void OnStartBtnClicked(View view) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
