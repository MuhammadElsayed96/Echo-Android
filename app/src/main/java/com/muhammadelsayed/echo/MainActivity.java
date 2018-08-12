/*
 *  Copyright [2017] [Muhammad Elsayed]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muhammadelsayed.echo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.muhammadelsayed.echo.Fragments.App.HomeFragment;
import com.muhammadelsayed.echo.Fragments.App.SearchFragment;
import com.muhammadelsayed.echo.Fragments.App.SettingsFragment;
import com.muhammadelsayed.echo.Fragments.App.ShortcutsFragment;
import com.thefinestartist.Base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String TAG_FRAGMENT_HOME = "tag_frag_home";
    private static final String TAG_FRAGMENT_SHORTCUTS = "tag_frag_shortcuts";
    private static final String TAG_FRAGMENT_SEARCH = "tag_frag_search";
    private static final String TAG_FRAGMENT_SETTINGS = "tag_frag_settings";
    private static final int INT_FRAGMENTS_COUNT = 4;
    private static final int INT_FRAGMENT_HOME_POS = 0;
    private static final int INT_FRAGMENT_SHORTCUTS_POS = 1;
    private static final int INT_FRAGMENT_SEARCH_POS = 2;
    private static final int INT_FRAGMENT_SETTINGS_POS = 3;
    public BottomNavigationView mBottomNavigation;
    private List<Fragment> mFragmentsList = new ArrayList<>(INT_FRAGMENTS_COUNT);
    private SweetAlertDialog exit;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            switchFragment(INT_FRAGMENT_HOME_POS, TAG_FRAGMENT_HOME);
                            return true;
                        case R.id.navigation_shortcuts:
                            switchFragment(INT_FRAGMENT_SHORTCUTS_POS, TAG_FRAGMENT_SHORTCUTS);
                            return true;
                        case R.id.navigation_search:
                            switchFragment(INT_FRAGMENT_SEARCH_POS, TAG_FRAGMENT_SEARCH);
                            return true;
                        case R.id.navigation_settings:
                            switchFragment(INT_FRAGMENT_SETTINGS_POS, TAG_FRAGMENT_SETTINGS);
                            return true;
                    }
                    return false;
                }
            };

    /**
     * This method will force the BottomNavigationView to show both the icon and the label of each
     * element in the BottomNavigationView, not only the highlighted element
     * <p>
     * <p>I got this method from STACKOVERFLOW.com and here's the link see <a
     * href="https://stackoverflow.com/questions/41352934/force-showing-icon-and-title-in-bottomnavigationview-support-android/41374515"</a>
     *
     * @param view is the BottomNavigationView object on which the force showing will be applied
     */
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        Log.wtf(TAG, "disableShiftMode() has been instantiated");

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            // Timber.e(e, "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            // Timber.e(e, "Unable to change value of shift mode");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate(): has been instantiated");
        Base.initialize(this);
        mBottomNavigation = findViewById(R.id.bottom_navigation_view);
        mBottomNavigation.getMenu().getItem(0).setChecked(true);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        disableShiftMode(mBottomNavigation);
        buildFragmentsList();
        // Set the 0th Fragment to be displayed by default.
        switchFragment(0, TAG_FRAGMENT_HOME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume(): has been instantiated");

        if (getIntent().hasExtra("from_notification")) {
            Log.d(TAG, "onResume: HAS");
            boolean fromNotification = getIntent().getBooleanExtra("from_notification", false);
            if (fromNotification) {
                Log.d(TAG, "onResume: fragment = " + HomeFragment.fragment);
                switchFragment(0, TAG_FRAGMENT_HOME);
                mBottomNavigation.setSelectedItemId(R.id.navigation_home);
                Log.d(TAG, "onResume: fragment = " + HomeFragment.fragment);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause(): has been instantiated");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy(): has been instantiated");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop(): has been instantiated");
    }

    /**
     * Use this method to create a new instance of any of the fragments using the fragments' factory
     * methods.
     * <p>
     * <p>Then, the method puts the created fragments in the "mFragmentsList"
     */
    private void buildFragmentsList() {
        Log.wtf(TAG, "buildFragmentsList() has been instantiated");

        HomeFragment homeFragment = HomeFragment.homeFragmentInstance();
        ShortcutsFragment shortcutsFragment = ShortcutsFragment.shortcutsFragmentInstance();
        SearchFragment searchFragment = SearchFragment.searchFragmentInstance();
        SettingsFragment settingsFragment = SettingsFragment.settingsFragmentInstance();

        mFragmentsList.add(homeFragment);
        mFragmentsList.add(shortcutsFragment);
        mFragmentsList.add(searchFragment);
        mFragmentsList.add(settingsFragment);
    }

    /**
     * This generic method is used to handle switching between fragments.
     *
     * @param pos The position of fragment at the "mFragmentList".
     * @param tag The tag name for the fragment.
     */
    private void switchFragment(int pos, String tag) {
        Log.wtf(TAG, "switchFragment() has been instantiated");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragment_holder, mFragmentsList.get(pos), tag)
                .commit();
    }


    @Override
    public void onBackPressed() {
        Log.wtf(TAG, "onBackPressed() has been instantiated");
        if (exit != null)
            exit = null;
        exit = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
        exit.setCancelable(false);
        exit.setTitleText("Exit app")
                .setContentText("Are you sure you want close the app?")
                .setConfirmText("YES")
                .setCancelText("CANCEL")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finish();
                HomeFragment.fragment = null;
            }
        }).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}