package com.tuan.vocabulary.ultis;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by tuanl on 28-Feb-17.
 */
public class Ultis {
    private static Ultis ourInstance = new Ultis();

    public static Ultis getInstance() {
        return ourInstance;
    }

    private Ultis() {
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
