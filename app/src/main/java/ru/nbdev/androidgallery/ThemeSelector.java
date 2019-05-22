package ru.nbdev.androidgallery;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

public class ThemeSelector {
    private static final String EXTRA_THEME = "EXTRA_THEME";
    private static @StyleRes
    int theme = R.style.MyThemeCyanIndigo;

    public static void selectThemeByMenuId(AppCompatActivity activity, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theme_cyan:
                ThemeSelector.theme = R.style.MyThemeCyanIndigo;
                break;

            case R.id.theme_purple:
                ThemeSelector.theme = R.style.MyThemeDeepPurplePink;
                break;

            case R.id.theme_green:
                ThemeSelector.theme = R.style.MyThemeGreenLime;
                break;
        }
        activity.recreate();
    }

    public static void setTheme(AppCompatActivity activity, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt(EXTRA_THEME);
        }
        activity.setTheme(theme);
    }

    public static void saveState(Bundle outState) {
        outState.putInt(EXTRA_THEME, ThemeSelector.theme);
    }

    public static void checkCurrentTheme(AppCompatActivity activity) {
        if (getThemeId(activity) != theme) {
            activity.recreate();
        }
    }

    private static int getThemeId(AppCompatActivity activity) {
        try {
            Class<?> wrapper = Context.class;
            Method method = wrapper.getMethod("getThemeResId");
            method.setAccessible(true);
            return (Integer) method.invoke(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
