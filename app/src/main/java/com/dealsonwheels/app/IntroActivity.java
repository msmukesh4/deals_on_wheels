package com.dealsonwheels.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.dealsonwheels.app.intro_fragments.IntroFragment1;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by mukesh on 12/11/17.
 */

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // hide status bar must be called before adding content
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        addSlide(IntroFragment1.newInstance(1,"",""));
        addSlide(IntroFragment1.newInstance(2,"",""));
        addSlide(IntroFragment1.newInstance(3,"",""));
        addSlide(IntroFragment1.newInstance(4,"",""));
//        addSlide(AppIntroFragment.newInstance("title", "desc", R.drawable.three, getResources().getColor(R.color.danger)));
//        addSlide(AppIntroFragment.newInstance("title", "desc", R.drawable.three, getResources().getColor(R.color.danger)));
//        addSlide(AppIntroFragment.newInstance("title", "desc", R.drawable.three, getResources().getColor(R.color.danger)));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        activityTransition();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        activityTransition();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    private void activityTransition() {
        Intent intent = new Intent(IntroActivity.this,Home.class);
        startActivity(intent);
        finish();
    }
}
