package com.example.codassistant.ViewPagers.RosterPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codassistant.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RostersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RostersFragment extends Fragment {

    private static final String ARG_PIC = "pic";
    private static final String ARG_NAME = "name";
    private static final String ARG_ROSTER = "roster";
    /**
     * The View pager 2.
     */
    ViewPager2 viewPager2;

    // TODO: Rename and change types of parameters
    private String pic;
    private String name;
    private String roster;

    /**
     * Instantiates a new Teams fragment.
     */
    public RostersFragment() {
        // Required empty public constructor
    }

    /**
     * New instance teams fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the teams fragment
     */
// TODO: Rename and change types and number of parameters
    public static RostersFragment newInstance(String param1, String param2) {
        RostersFragment fragment = new RostersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PIC, fragment.pic);
        args.putString(ARG_NAME, fragment.name);
        args.putString(ARG_ROSTER, fragment.roster);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pic = getArguments().getString(ARG_PIC);
            name = getArguments().getString(ARG_NAME);
            roster = getArguments().getString(ARG_ROSTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rosters, container, false);
        viewPager2 = view.findViewById(R.id.rostersViewPager);
        viewPager2.setAdapter(new CustomViewPager2Adapter(Objects.requireNonNull(getActivity()), getContext()));
        viewPager2.setPageTransformer(new DepthPageTransformer());
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        viewPager2.setAdapter(new CustomViewPager2Adapter(Objects.requireNonNull(getActivity()), getContext()));
        viewPager2.setPageTransformer(new DepthPageTransformer());
    }

    public static class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setTranslationZ(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Move it behind the left page
                view.setTranslationZ(-1f);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}
