package com.magmamobile.game.engine;

import android.view.animation.*;

public final class AnimationUtils
{

    public AnimationUtils()
    {
    }

    public static final android.view.animation.Animation createAnimationLeftIn(int i)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
        alphaanimation.setDuration(i);
        animationset.addAnimation(alphaanimation);
        TranslateAnimation translateanimation = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
        translateanimation.setDuration(i);
        translateanimation.setStartOffset(0L);
        animationset.addAnimation(translateanimation);
        return animationset;
    }

    public static final android.view.animation.Animation createAnimationLeftOut(int i)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
        alphaanimation.setDuration(i);
        animationset.addAnimation(alphaanimation);
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.0F, 1, -2F, 1, 0.0F, 1, 0.0F);
        translateanimation.setDuration(i);
        translateanimation.setStartOffset(0L);
        animationset.addAnimation(translateanimation);
        animationset.setFillAfter(true);
        return animationset;
    }

    public static final android.view.animation.Animation createAnimationRightIn(int i)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
        alphaanimation.setDuration(i);
        animationset.addAnimation(alphaanimation);
        TranslateAnimation translateanimation = new TranslateAnimation(1, -2F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
        translateanimation.setDuration(i);
        translateanimation.setStartOffset(0L);
        animationset.addAnimation(translateanimation);
        return animationset;
    }

    public static final android.view.animation.Animation createAnimationRightOut(int i)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.0F);
        alphaanimation.setDuration(i);
        animationset.addAnimation(alphaanimation);
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.0F, 1, 1.0F, 1, 0.0F, 1, 0.0F);
        translateanimation.setDuration(i);
        translateanimation.setStartOffset(0L);
        animationset.addAnimation(translateanimation);
        animationset.setFillAfter(true);
        return animationset;
    }
}
