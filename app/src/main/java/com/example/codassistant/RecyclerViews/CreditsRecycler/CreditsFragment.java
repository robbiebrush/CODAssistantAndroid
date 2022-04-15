package com.example.codassistant.RecyclerViews.CreditsRecycler;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codassistant.Database.pojos.Credit;
import com.example.codassistant.MainActivity;
import com.example.codassistant.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreditsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreditsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreditsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreditsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreditsFragment newInstance(String param1, String param2) {
        CreditsFragment fragment = new CreditsFragment();
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
        View view = inflater.inflate(R.layout.fragment_credits, container, false);
        ArrayList<Credit> credits = new ArrayList<>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));

        if (sharedPreferences.getString("font", "default").equals("default")) {
            MainActivity.font = 0;
        } else if (sharedPreferences.getString("font" ,"default").equals("large")) {
            MainActivity.font = 1;
        }

        String creators = getString(R.string.creators);
        String apiData = getString(R.string.apiData);
        String image = getString(R.string.codVangImage);
        String events = getString(R.string.home_eventsTitle);

        credits.add(new Credit(creators, "Robbie Brush, Evan"));
        credits.add(new Credit(apiData, "https://pandascore.co/stats"));
        credits.add(new Credit(image, "https://styles.redditmedia.com/t5_3sgon3/styles/communityIcon_m4hjnmn9nuw71.png?width=256&s=eec96e0cb80b1899e6088cea3e31293a424727a2"));
        credits.add(new Credit(events, "https://dotesports.com/call-of-duty/news/heres-the-2022-call-of-duty-league-season-schedule"));

        RecyclerView recyclerView = view.findViewById(R.id.creditsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CustomRecyclerViewAdapter2(credits));
        return view;
    }
}