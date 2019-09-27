package com.jumpingi.arithmetic.utils;

import android.content.Context;

public abstract class Question {
    private Context mContext;

    abstract void makeQuestion(int num);
    abstract void checkQuestion();

    protected void checkSameNumber() {

    }


}
