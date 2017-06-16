package com.theonepiano.smartpiano.splash;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;

import com.theonepiano.smartpiano.MainActivity;
import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.ui.splash.LangLangActivity;

import junit.framework.Assert;

import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.*;

/**
 * Created by jim on 2017/5/27.
 */
public class LangLangActivityTest {
    @Test
    public void onStartBtnClicked() throws Exception {
        LangLangActivity activity = Robolectric.setupActivity(LangLangActivity.class);

        Button startBtn = (Button)activity.findViewById(R.id.start_btn);
        startBtn.performClick();

        Intent expectIntent = new Intent(activity, MainActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        Assert.assertNotNull(actualIntent);
        Assert.assertEquals(expectIntent.getComponent().getClassName(), actualIntent.getComponent().getClassName());
    }

}