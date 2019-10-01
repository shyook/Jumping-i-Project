package com.jumpingi.arithmetic.utils;

import android.content.Context;

import com.jumpingi.arithmetic.R;

public class UiUtils {
    private UiUtils() {

    }

    public static String convertNameToSign(int resId, Context context) {
        switch (resId) {
            case R.string.menu_addition:
                return context.getString(R.string.addition_operator);

            case R.string.menu_subtraction:
                return context.getString(R.string.subtraction_operator);

            case R.string.menu_multiply:
                return context.getString(R.string.multiply_operator);

            case R.string.menu_divide:
                return context.getString(R.string.divide_operator);

            default:
                return "";
        }
    }
}
