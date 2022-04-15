package com.example.codassistant.RecyclerViews.MatchesRecycler;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codassistant.Database.MatchesDatabase;
import com.example.codassistant.Database.pojos.Match;
import com.example.codassistant.MainActivity;
import com.example.codassistant.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MatchesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchesFragment newInstance(String param1, String param2) {
        MatchesFragment fragment = new MatchesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));
        if (sharedPreferences.getString("font", "default").equals("default")) {
            MainActivity.font = 0;
        } else if (sharedPreferences.getString("font" ,"default").equals("large")) {
            MainActivity.font = 1;
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.matchesRecyclerView);
        MatchesDatabase db = new MatchesDatabase(getContext());

        TextView instructs = view.findViewById(R.id.instructText);
        if (MainActivity.font == 0) {
            instructs.setTextSize(getResources().getDimension(R.dimen.subtitle_text) / getResources().getDisplayMetrics().density);
        } else if (MainActivity.font == 1) {
            instructs.setTextSize(getResources().getDimension(R.dimen.subtitle_text_large) / getResources().getDisplayMetrics().density);
        }

        CustomRecyclerViewAdapter2 adapter = new CustomRecyclerViewAdapter2(db.getAllMatches(), getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}