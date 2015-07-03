package com.steve.theshortreport.ui;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * Created by steve-donnelly on 7/3/15.
 */
public class FadeOutAnimationListener implements AnimationListener {

    private View myView;

    public FadeOutAnimationListener(View view){
        myView = view;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        myView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
