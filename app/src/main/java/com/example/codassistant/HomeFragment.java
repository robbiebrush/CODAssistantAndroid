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

        Button eventButt1 = view.findViewById(R.id.calButt1);
        Button eventButt2 = view.findViewById(R.id.calButt2);
        Button eventButt3 = view.findViewById(R.id.calButt3);

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