package com.muhammadelsayed.echo.settings_fragment.FilterNewsTabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.muhammadelsayed.echo.R;

public class HeadlinesTab extends Fragment {
    private static final String TAG = "HeadlinesTab";
    public static CheckBox chkAustralia, chkUk, chkUs, chkInternational;
    public static boolean au, uk, us, international;
    private static final int MAX_CHECKED_NUM = 4;
    private static final int MIN_CHECKED_NUM = 1;
    private SharedPreferences sharedpreferences;
    private RelativeLayout mAustralia, mUk, mUs, mInternational;
    private int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_headlines_tab, container, false);
        initViews(rootView);
        initCheckBoxes();
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        chkAustralia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("au_enabled", chkAustralia.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });
        chkUk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("uk_enabled", chkUk.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });
        chkUs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("us_enabled", chkUs.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });
        chkInternational.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("international_enabled", chkInternational.isChecked());
                editor.apply();
                countCheckedBox(b);
                validateCheckBoxes();
            }
        });
        return rootView;
    }

    private void initViews(View rootView) {
        mAustralia = rootView.findViewById(R.id.auheadlines);
        mUk = rootView.findViewById(R.id.ukheadlines);
        mUs = rootView.findViewById(R.id.usheadlines);
        mInternational = rootView.findViewById(R.id.headlines);

        chkAustralia = rootView.findViewById(R.id.check_auheadlines);
        chkUk = rootView.findViewById(R.id.check_ukheadlines);
        chkUs = rootView.findViewById(R.id.check_usheadlines);
        chkInternational = rootView.findViewById(R.id.check_headlines);
    }

    private void initCheckBoxes() {
        count = 0;
        sharedpreferences = getActivity().getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);
        au = sharedpreferences.getBoolean("au_enabled", true);
        uk = sharedpreferences.getBoolean("uk_enabled", true);
        us = sharedpreferences.getBoolean("us_enabled", true);
        international = sharedpreferences.getBoolean("international_enabled", true);

        initCount(au);
        initCount(uk);
        initCount(us);
        initCount(international);

        chkAustralia.setChecked(au);
        chkUk.setChecked(uk);
        chkUs.setChecked(us);
        chkInternational.setChecked(international);
    }

    private void initCount(boolean b) {
        if (b)
            count++;
    }

    private void countCheckedBox(boolean b) {
        if (b) {
            if (count < MAX_CHECKED_NUM)
                count++;
        } else if (count >= MIN_CHECKED_NUM) {
            count--;
        }
    }

    private void validateCheckBoxes() {
        Log.d(TAG, "validateCheckBoxes: COUNT = " + count);
        if (count <= MIN_CHECKED_NUM) {
            chkAustralia.setEnabled(!chkAustralia.isChecked());
            chkUk.setEnabled(!chkUk.isChecked());
            chkUs.setEnabled(!chkUs.isChecked());
            chkInternational.setEnabled(!chkInternational.isChecked());
        } else {
            chkAustralia.setEnabled(true);
            chkUk.setEnabled(true);
            chkUs.setEnabled(true);
            chkInternational.setEnabled(true);
        }
    }
}
