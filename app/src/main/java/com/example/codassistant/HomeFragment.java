package com.example.codassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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

        Button eventButt1 = view.findViewById(R.id.calButt1);
        Button eventButt2 = view.findViewById(R.id.calButt2);
        Button eventButt3 = view.findViewById(R.id.calButt3);

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

        eventButt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, getContext().getResources().getString(R.string.intentInsertEvent4))
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, getContext().getResources().getString(R.string.tbd))
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1654131600)
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1654520400);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        eventButt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, getContext().getResources().getString(R.string.intentInsertEvent5))
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, getContext().getResources().getString(R.string.tbd))
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1658365200)
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1658710800);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        eventButt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, getContext().getResources().getString(R.string.intentInsertEvent6))
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, getContext().getResources().getString(R.string.tbd))
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1660870800)
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1661216400);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}