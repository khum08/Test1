package com.example.khum.demo0223.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * <pre>
 *     author : khum
 *     time   : 2018/4/26
 *     desc   : SnackbarUtil
 * </pre>
 */
public interface SnackbarUtil {

    //=====================Snackbar的基本使用===============================
    static void showLong(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    static void showShort(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    static void showAlways(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).show();
    }

    //=====================Snackbar 自定义颜色===============================
    static Snackbar showLongWithColor(View view, CharSequence text, int color) {
        return Snackbar.make(view, text, Snackbar.LENGTH_LONG).setActionTextColor(color);
    }

    static Snackbar showShortWithColor(View view, CharSequence text, int color) {
        return Snackbar.make(view, text, Snackbar.LENGTH_SHORT).setActionTextColor(color);
    }

    static Snackbar showAlwaysWithColor(View view, CharSequence text, int color) {
        return Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setActionTextColor(color);
    }

}
