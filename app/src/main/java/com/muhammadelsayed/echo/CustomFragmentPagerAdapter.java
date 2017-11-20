package com.muhammadelsayed.echo;
/*
* Copyright [2017] [Muhammad Elsayed]

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
**/

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 11;
    private String tabTitles[] = new String[]{"World", "National", "Politics", "Business", "Technology", "Environment", "LifeStyle", "Health", "Sport", "Science", "Travel"};
    private Context context;


    public CustomFragmentPagerAdapter(FragmentManager FM, Context context) {
        super(FM);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new WorldNewsFragment();
        } else if (position == 1) {
            return new NationalNewsFragment();
        } else if (position == 2) {
            return new PoliticsNewsFragment();
        } else if (position == 3) {
            return new BusinessNewsFragment();
        } else if (position == 4) {
            return new TechNewsFragment();
        } else if (position == 5) {
            return new EnvironmentNewsFragment();
        } else if (position == 6) {
            return new LifeStyleNewsFragment();
        } else if (position == 7) {
            return new HealthNewsFragment();
        } else if (position == 8) {
            return new SportNewsFragment();
        } else if (position == 9) {
            return new ScienceNewsFragment();
        } else {
            return new TravelNewsFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
