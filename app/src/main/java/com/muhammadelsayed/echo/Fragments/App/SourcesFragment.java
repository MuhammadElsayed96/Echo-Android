package com.muhammadelsayed.echo.Fragments.App;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muhammadelsayed.echo.Adapters.SourcesAdapter;
import com.muhammadelsayed.echo.R;

import static com.muhammadelsayed.echo.SplashActivity.mSourcesList;

/**
 * A simple {@link Fragment} subclass. Use the {@link SourcesFragment#sourcesFragmentInstance}
 * factory method to create an instance of this fragment.
 */
public class SourcesFragment extends Fragment {

  private static final String TAG = "SourcesFragment";

  private View rootView;
  private RecyclerView mSourcesRecyclerView;
  private SourcesAdapter mSourcesAdapter;

  public SourcesFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @return A new instance of fragment SourcesFragment.
   */
  public static SourcesFragment sourcesFragmentInstance() {
    SourcesFragment fragment = new SourcesFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    rootView = inflater.inflate(R.layout.fragment_sources, container, false);
    mSourcesRecyclerView = rootView.findViewById(R.id.recycler_view);

    RecyclerView.LayoutManager mLayoutManager =
        new LinearLayoutManager(getActivity().getApplicationContext());
    mSourcesRecyclerView.setHasFixedSize(true);
    mSourcesRecyclerView.setLayoutManager(mLayoutManager);
    mSourcesRecyclerView.setDrawingCacheEnabled(true);
    mSourcesRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
    mSourcesRecyclerView.setItemViewCacheSize(20);
    mSourcesRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mSourcesRecyclerView.addItemDecoration(
        new DividerItemDecoration(
            getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));

    mSourcesAdapter = new SourcesAdapter(getActivity(), mSourcesList);
    mSourcesRecyclerView.setAdapter(mSourcesAdapter);


    return rootView;
  }

}
