package com.example.codassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.provider.CalendarContract;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    public static String titleStr = "";
    public static int start = 0;
    public static int end = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView homeTitle = view.findViewById(R.id.homeTitle);
        TextView homeDesc = view.findViewById(R.id.home_description);
        TextView matchesDesc = view.findViewById(R.id.matches_info);
        TextView homeCreate = view.findViewById(R.id.home_create);
        TextView homeEdit = view.findViewById(R.id.home_edit);
        TextView homeDelete = view.findViewById(R.id.home_delete);
        TextView homeStatsTitle = view.findViewById(R.id.home_statsTitle);
        TextView homeStatsDesc = view.findViewById(R.id.home_statsDescription);
        TextView eventTitle = view.findViewById(R.id.home_eventsTitle);
        TextView event1 = view.findViewById(R.id.event1_info);
        TextView event2 = view.findViewById(R.id.event2_info);
        TextView event3 = view.findViewById(R.id.event3_info);
        TextView event4 = view.findViewById(R.id.event4_info);
        TextView event5 = view.findViewById(R.id.event5_info);
        TextView event6 = view.findViewById(R.id.event6_info);

        if (MainActivity.font == 0) {
            homeTitle.setTextSize(getResources().getDimension(R.dimen.title_text) / getResources().getDisplayMetrics().density);
            homeDesc.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            matchesDesc.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            homeCreate.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            homeEdit.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            homeDelete.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            homeStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text) / getResources().getDisplayMetrics().density);
            homeStatsDesc.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            eventTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text) / getResources().getDisplayMetrics().density);
            event1.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            event2.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            event3.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            event4.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            event5.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
            event6.setTextSize(getResources().getDimension(R.dimen.body_text) / getResources().getDisplayMetrics().density);
        } else if (MainActivity.font == 1) {
            homeTitle.setTextSize(getResources().getDimension(R.dimen.title_text_large) / getResources().getDisplayMetrics().density);
            homeDesc.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            matchesDesc.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            homeCreate.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            homeEdit.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            homeDelete.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            homeStatsTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text_large) / getResources().getDisplayMetrics().density);
            homeStatsDesc.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            eventTitle.setTextSize(getResources().getDimension(R.dimen.subtitle_text_large) / getResources().getDisplayMetrics().density);
            event1.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            event2.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            event3.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            event4.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            event5.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
            event6.setTextSize(getResources().getDimension(R.dimen.body_text_large) / getResources().getDisplayMetrics().density);
        }

        event4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event6.setTextColor(getResources().getColor(R.color.matches_color));
                event6.setBackgroundColor(getResources().getColor(R.color.white));
                event5.setTextColor(getResources().getColor(R.color.matches_color));
                event5.setBackgroundColor(getResources().getColor(R.color.white));
                event4.setTextColor(getResources().getColor(R.color.white));
                event4.setBackgroundColor(getResources().getColor(R.color.matches_color));

                titleStr = getResources().getString(R.string.home_event4);
                start = 1654142400;
                end = 1654488000;
            }
        });

        event5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event4.setTextColor(getResources().getColor(R.color.matches_color));
                event4.setBackgroundColor(getResources().getColor(R.color.white));
                event6.setTextColor(getResources().getColor(R.color.matches_color));
                event6.setBackgroundColor(getResources().getColor(R.color.white));
                event5.setTextColor(getResources().getColor(R.color.white));
                event5.setBackgroundColor(getResources().getColor(R.color.matches_color));

                titleStr = getResources().getString(R.string.home_event5);
                start = 1658376000;
                end = 1658721600;
            }
        });

        event6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event5.setTextColor(getResources().getColor(R.color.matches_color));
                event5.setBackgroundColor(getResources().getColor(R.color.white));
                event4.setTextColor(getResources().getColor(R.color.matches_color));
                event4.setBackgroundColor(getResources().getColor(R.color.white));
                event6.setTextColor(getResources().getColor(R.color.white));
                event6.setBackgroundColor(getResources().getColor(R.color.matches_color));

                titleStr = getResources().getString(R.string.home_event6);
                start = 1660881600;
                end = 1661227200;
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        titleStr = "";
        start = 0;
        end = 0;
    }
}