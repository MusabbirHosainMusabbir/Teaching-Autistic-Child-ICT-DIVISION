package com.teachingchild.teachingautisticchild.Utils;

import android.view.animation.Interpolator;

public class MyBounce implements Interpolator {
    private double myAmplitude=2;
    private double myFrequency =7;

    public MyBounce(double amplitude, double frequency){
        this.myAmplitude = amplitude;
        this.myFrequency = frequency;
    }
    @Override
    public float getInterpolation(float time) {
        return (float)(-1*Math.pow(Math.E, -time/myAmplitude)*Math.cos(myFrequency*time)*1);
    }
}
