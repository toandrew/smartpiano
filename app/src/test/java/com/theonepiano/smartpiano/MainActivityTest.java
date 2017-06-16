package com.theonepiano.smartpiano;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.theonepiano.smartpiano.ui.guide.GuideActivity;
import com.theonepiano.smartpiano.ui.splash.LangLangActivity;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jim on 2017/5/26.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, shadows = {ShadowLog.class})
public class MainActivityTest {
    MainActivity mActivity;

    private boolean mIsFirst = false;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        mActivity = Robolectric.setupActivity(MainActivity.class);

        mIsFirst = mActivity.isFirst();
    }

    @After
    public void cleanUp() {
        mActivity.setFirst(mIsFirst);
    }

    // 程序开始时，如果是首次打开饮用，则显示朗朗视频
    @Test
    public void displayLangLangVideo() {
        mActivity.setFirst(false);
        mActivity.displayScene();

        Intent expectIntent = new Intent(mActivity, LangLangActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        Assert.assertNotNull(actualIntent);
        Assert.assertEquals(expectIntent.getComponent().getClassName(), actualIntent.getComponent().getClassName());
    }

    // 显示引导页
    @Test
    public void displayIntroductionPage() {
        mActivity.setFirst(true);
        mActivity.displayScene();

        Intent expectIntent = new Intent(mActivity, GuideActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        Assert.assertNotNull(actualIntent);
        Assert.assertEquals(expectIntent.getComponent().getClassName(), actualIntent.getComponent().getClassName());
    }

    // 首次显示时，需要修改first标识
    @Test
    public void changeFistStartFlag() {
        mActivity.setFirst(true);
        Assert.assertEquals(true, mActivity.isFirst());

        mActivity.setFirst(false);
        Assert.assertEquals(false, mActivity.isFirst());
    }

    // 启动时在指定时间内显示指定的背景图
    @Ignore
    public void displayTimedBackground() {
        mActivity.delayShow();

        final CountDownLatch latch = new CountDownLatch(1);
        // 2s定时任务
        TimerTask task = new TimerTask(){

            public void run(){
                latch.countDown();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 10000);

        // 刚开始处于显示状态
        View logoBg = mActivity.findViewById(R.id.bg);
        Assert.assertEquals(ImageView.VISIBLE, logoBg.getVisibility());

        ImageView logo = (ImageView) mActivity.findViewById(R.id.logo);
        Assert.assertEquals(ImageView.VISIBLE, logo.getVisibility());

        // 等待
        try {
//            latch.await();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断当前activity已经不是MainActivity
        ShadowActivity shadowActivity = Shadows.shadowOf(mActivity);
        shadowActivity.getCallingActivity();
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        Assert.assertNotNull(actualIntent);
        Assert.assertNotSame(mActivity.getComponentName().getClassName(), actualIntent.getComponent().getClassName());
    }
}