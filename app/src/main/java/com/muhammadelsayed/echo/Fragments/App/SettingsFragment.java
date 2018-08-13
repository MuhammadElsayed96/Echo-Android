package com.muhammadelsayed.echo.Fragments.App;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.SettingsFragment.AccountActivity;
import com.muhammadelsayed.echo.SettingsFragment.ContactUsActivity;
import com.muhammadelsayed.echo.SettingsFragment.DefaultEditionActivity;
import com.muhammadelsayed.echo.SettingsFragment.FilterNewsActivity;
import com.muhammadelsayed.echo.SettingsFragment.NotificationsActivity;
import com.muhammadelsayed.echo.SettingsFragment.ReadArticleActivity;
import com.muhammadelsayed.echo.SettingsFragment.ReorderSectionsActivity;

public class SettingsFragment extends Fragment {
    private static final String TAG = "SettingsFragment";
    private View rootView;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment settingsFragmentInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.wtf(TAG, "onCreateView() has been instantiated");
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        initiateViews();

        return rootView;
    }

    private void initiateViews() {
        Log.wtf(TAG, "initiateViews() has been instantiated");
        ConstraintLayout mAccount = rootView.findViewById(R.id.my_account_layout);
        ConstraintLayout mNewsFilter = rootView.findViewById(R.id.news_filter_layout);
        ConstraintLayout mEdition = rootView.findViewById(R.id.default_edition_layout);
        ConstraintLayout mArticle = rootView.findViewById(R.id.read_article_layout);
        ConstraintLayout mContact = rootView.findViewById(R.id.contact_layout);
        ConstraintLayout mReorder = rootView.findViewById(R.id.reorder_news_layout);
        ConstraintLayout mNotifications = rootView.findViewById(R.id.notifications_layout);

        mAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AccountActivity.class));
            }
        });


        mNewsFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FilterNewsActivity.class));
            }
        });

        mReorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReorderSectionsActivity.class));
            }
        });

        mEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DefaultEditionActivity.class));
            }
        });

        mArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReadArticleActivity.class));
            }
        });

        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ContactUsActivity.class));
            }
        });

        mNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NotificationsActivity.class));
            }
        });
    }
}
