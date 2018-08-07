package com.muhammadelsayed.echo.SettingsFragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muhammadelsayed.echo.R;

public class ReorderSectionsActivity extends AppCompatActivity {
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.muhammadelsayed.echo.Adapters.SectionAdapter;
import com.muhammadelsayed.echo.Adapters.helpers.OnSectionsListChangedListener;
import com.muhammadelsayed.echo.Adapters.helpers.OnStartDragListener;
import com.muhammadelsayed.echo.Adapters.helpers.SimpleItemTouchHelperCallback;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Section;

import java.util.ArrayList;
import java.util.List;

public class ReorderSectionsActivity extends AppCompatActivity implements OnSectionsListChangedListener,
        OnStartDragListener {
    private static final String TAG = "ReorderSectionsActivity";

    private SectionAdapter mSectionAdapter;
    private RecyclerView mSectionsRecycler;
    private ItemTouchHelper mItemTouchHelper;
    private List<Section> mSections;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_sections);

        mSharedPreferences = getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE);


        mSectionsRecycler = findViewById(R.id.recycler);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSectionsRecycler.setLayoutManager(mLinearLayoutManager);
        mSectionsRecycler.setDrawingCacheEnabled(true);
        mSectionsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mSectionsRecycler.setItemViewCacheSize(100);


        mSections = getSectionsList();

        mSectionAdapter = new SectionAdapter(this, mSections, this, this);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mSectionAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mSectionsRecycler);
        mSectionsRecycler.setAdapter(mSectionAdapter);
    }


    private List<Section> initSectionsList() {
        List<Section> sections = new ArrayList<>();

        sections.add(new Section("nav_au_news", "Australia Headlines"));
        sections.add(new Section("nav_uk_news", "UK Headlines"));
        sections.add(new Section("nav_us_news", "US Headlines"));
        sections.add(new Section("nav_news", "International Headlines"));
        sections.add(new Section("nav_art_design", "Art & Design"));
        sections.add(new Section("nav_books", "Books"));
        sections.add(new Section("nav_business", "Business"));
        sections.add(new Section("nav_culture", "Culture"));
        sections.add(new Section("nav_education", "Education"));
        sections.add(new Section("nav_environment", "Environment"));
        sections.add(new Section("nav_fashion", "Fashion"));
        sections.add(new Section("nav_film", "Film"));
        sections.add(new Section("nav_football", "Football"));
        sections.add(new Section("nav_law", "Law"));
        sections.add(new Section("nav_lifestyle", "Lifestyle"));
        sections.add(new Section("nav_media", "Media"));
        sections.add(new Section("nav_money", "Money"));
        sections.add(new Section("nav_music", "Music"));
        sections.add(new Section("nav_politics", "Politics"));
        sections.add(new Section("nav_science", "Science"));
        sections.add(new Section("nav_society", "Society"));
        sections.add(new Section("nav_sport", "Sport"));
        sections.add(new Section("nav_technology", "Technology"));
        sections.add(new Section("nav_travel", "Travel"));
        sections.add(new Section("nav_tv_radio", "Tv & Radio"));
        sections.add(new Section("nav_weather", "Weather"));

        return sections;
    }

    private void createListofSortedSectionIds() {
        List<String> sortedIds = new ArrayList<>();
        for (Section section : mSections) {
            sortedIds.add(section.getId());
        }

        Gson gson = new Gson();
        String jsonListOfSortedSectionsIds = gson.toJson(sortedIds);

        mEditor = mSharedPreferences.edit();

        mEditor.putString("list_of_sorted_sections_ids", jsonListOfSortedSectionsIds).apply();
    }

    private List<Section> getSectionsList() {
        List<Section> sectionsList = initSectionsList();
        List<Section> sortedSectionsList = new ArrayList<>();

        String jsonListOfSortedSectionsIds = mSharedPreferences.getString("list_of_sorted_sections_ids", "");

        if (!jsonListOfSortedSectionsIds.isEmpty()) {

            Gson gson = new Gson();
            List<String> listOfSortedSectionssId = gson.fromJson(jsonListOfSortedSectionsIds, new TypeToken<List<String>>(){}.getType());

            if (listOfSortedSectionssId != null && listOfSortedSectionssId.size() > 0) {

                for (String id : listOfSortedSectionssId) {
                    for (Section section : sectionsList) {
                        if (section.getId().equals(id)) {
                            sortedSectionsList.add(section);
                            sectionsList.remove(section);
                            break;
                        }
                    }
                }

            }

            if (sectionsList.size() > 0) {
                sortedSectionsList.addAll(sectionsList);
            }

            return sortedSectionsList;

        } else {
            return sectionsList;
        }

    }

    @Override
    public void onNoteListChanged(List<Section> customers) {
        createListofSortedSectionIds();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
