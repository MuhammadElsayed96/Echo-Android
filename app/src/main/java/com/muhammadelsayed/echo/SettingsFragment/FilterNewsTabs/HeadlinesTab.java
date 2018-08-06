package com.muhammadelsayed.echo.SettingsFragment.FilterNewsTabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.muhammadelsayed.echo.R;

public class HeadlinesTab extends Fragment {
    private static final String TAG = "HeadlinesTab";
    private SharedPreferences sharedpreferences;

    private RelativeLayout mAustralia, mUk, mUs, mInternational;

    private CheckBox chkAustralia, chkUk, chkUs, chkInternational;

    private boolean au, uk, us, international;

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
            }
        });

        chkUk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("uk_enabled", chkUk.isChecked());
                editor.apply();
            }
        });

        chkUs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("us_enabled", chkUs.isChecked());
                editor.apply();
            }
        });

        chkInternational.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("international_enabled", chkInternational.isChecked());
                editor.apply();
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
        sharedpreferences = getActivity().getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);

        au = sharedpreferences.getBoolean("au_enabled", true);
        uk = sharedpreferences.getBoolean("uk_enabled", true);
        us = sharedpreferences.getBoolean("us_enabled", true);
        international = sharedpreferences.getBoolean("international_enabled", true);

        if (au)
            chkAustralia.setChecked(true);
        if (uk)
            chkUk.setChecked(true);
        if (us)
            chkUs.setChecked(true);
        if (international)
            chkInternational.setChecked(true);

    }
    
}
