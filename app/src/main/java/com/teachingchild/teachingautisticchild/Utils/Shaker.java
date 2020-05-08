package com.teachingchild.teachingautisticchild.Utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.Animation;

public class Shaker {

    private ObjectAnimator shaker;

    @SuppressLint("WrongConstant")
    public Shaker(View view, float x, float xDelta){

        shaker = ObjectAnimator.ofFloat(view, "translationX", x, xDelta);
        shaker.setDuration(30);
        shaker.setRepeatCount(5);
        shaker.setRepeatMode(Animation.REVERSE);



    }

    public void shake(){
        AnimatorSet set = new AnimatorSet();
        set.play(shaker);
        set.start();
    }


}
