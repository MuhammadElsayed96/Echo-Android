package com.muhammadelsayed.echo.Fragments.HomeTabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muhammadelsayed.echo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LawFragment extends Fragment {

    public LawFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LawFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LawFragment newInstance(String param1, String param2) {
        LawFragment fragment = new LawFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

}
