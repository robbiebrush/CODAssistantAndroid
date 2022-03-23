package com.example.codassistant.ViewPagers.RosterPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codassistant.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPager2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPager2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PIC = "pic";
    private static final String ARG_NAME = "name";
    private static final String ARG_ROSTER = "roster";

    // TODO: Rename and change types of parameters
    private String pic;
    private String name;
    private String roster;

    /**
     * Instantiates a new View page fragment.
     */
    public ViewPager2Fragment() {
        // Required empty public constructor
    }

    /**
     * New instance view page fragment.
     *
     * @param pic  the pic
     * @param name the name
     * @return the view page fragment
     */
// TODO: Rename and change types and number of parameters
    public static ViewPager2Fragment newInstance(String pic, String name, String roster) {
        ViewPager2Fragment fragment = new ViewPager2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PIC, pic);
        args.putString(ARG_NAME, name);
        args.putString(ARG_ROSTER, roster);
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
        View view = inflater.inflate(R.layout.fragment_view_pager2, container, false);
        ImageView picView = (ImageView) view.findViewById(R.id.teamPic);
        TextView nameTextView = (TextView) view.findViewById(R.id.teamName);
        TextView rosterTextView = (TextView) view.findViewById(R.id.teamRoster);

        Picasso.get().load(pic).into(picView);
        nameTextView.setText(name);
        rosterTextView.setText(roster);

        return view;
    }
}